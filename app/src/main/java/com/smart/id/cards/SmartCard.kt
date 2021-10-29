package com.smart.id.cards

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.smart.id.cards.data.Attendance
import com.smart.id.cards.data.AttendanceListener
import com.smart.id.cards.data.Student
import kotlinx.android.synthetic.main.activity_smart_card.*
import java.text.SimpleDateFormat
import java.util.*

class SmartCard : AppCompatActivity(),AttendanceListener {
    var student:Student ? = null
    var database:FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    var today:String? = null
    var todayWithDateTime:String ? = null
    var attendanceList:MutableList<Attendance> = arrayListOf()
    var isUserget = false
    var TAG = "SmartCard"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_card)
        student = Gson().fromJson(intent.getStringExtra("data"), Student::class.java)
        var qrString = student?.firstName+"+"+student?.lastName+"+"+student?.geder+"+"+student?.username+"+"+student?.emiratesID
        var qrCodeBitmap = getQrCodeBitmap(qrString)
        iv_qr_image.setImageBitmap(qrCodeBitmap)
        tv_name.text = student?.firstName+" "+student?.lastName
        tv_emirates_id.text = "Emirates ID: "+student?.emiratesID
        tv_username.text = "Username: "+student?.username
        tv_type.text = "Type: "+student?.userType
        var notMatched = true
        database = FirebaseDatabase.getInstance("https://smart-id-card-f2eab-default-rtdb.firebaseio.com/")
        today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        todayWithDateTime = today + " "+SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date());
        databaseReference = database?.getReference("attendance")
        databaseReference?.child(today!!)!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                attendanceList.clear()
                Log.i(TAG,"Snapshot: "+snapshot.children)
                for(it in snapshot.children) {
                    attendanceList.add(it.getValue(Attendance::class.java)!!)
                }
                if(attendanceList.size > 0){
                    Log.i(TAG,"Has attendance")
                    for(i in attendanceList){
                        Log.i(TAG,"In Loop")
                        Log.i(TAG,"ServerUsername: ${i.studentUsername}, LocalUserName: ${student!!.username}")
                        if(i.studentUsername.equals(student!!.username)){
                            Log.i(TAG,"user matched")
                            notMatched= false
                            if(i.studentIn.isNullOrEmpty()){
                                // If the student not in
                                Log.i(TAG,"User didn't in yet")
                                databaseReference?.child(today!!)?.child(student!!.username)?.setValue(Attendance(
                                        todayWithDateTime!!,
                                        "",
                                        student!!.username,
                                        student!!.firstName+" "+student!!.lastName
                                ))
                                break
                            }
                            else{
                                // If student is in but not out yet
                                Log.i(TAG,"User in for out")
                                databaseReference?.child(today!!)?.child(student!!.username)?.setValue(Attendance(
                                        i.studentIn,
                                        todayWithDateTime!!,
                                        student!!.username,
                                        student!!.firstName+" "+student!!.lastName
                                ))
                                break
                            }
                        }
                    }
                    if(notMatched){

                            // If the student is not yet appear in the campus
                            Log.i(TAG,"No user appear in the campus")
                            databaseReference?.child(today!!)?.child(student!!.username)?.setValue(Attendance(
                                    todayWithDateTime!!,
                                    "",
                                    student!!.username,
                                    student!!.firstName+" "+student!!.lastName
                            ))


                    }
                }else{
                    // If no user created yet
                    Log.i(TAG,"Very first time")
                    databaseReference?.child(today!!)?.child(student!!.username)?.setValue(Attendance(
                            todayWithDateTime!!,
                            "",
                            student!!.username,
                            student!!.firstName+" "+student!!.lastName
                    ))

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })






    }

    fun getQrCodeBitmap(code: String): Bitmap {
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(code, BarcodeFormat.QR_CODE, size, size, hints)

        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLUE else Color.WHITE)
                }
            }
        }
    }

    override fun onSuccess(list: MutableList<Attendance>) {

    }

    override fun onFailure(message: String) {

    }

    override fun onProgress() {

    }
}