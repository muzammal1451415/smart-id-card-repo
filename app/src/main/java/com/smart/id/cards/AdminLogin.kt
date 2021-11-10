package com.smart.id.cards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.gson.Gson
import com.smart.id.cards.data.AdminUser
import com.smart.id.cards.data.Student
import com.smart.id.cards.utils.SmartCareSharedPreferences
import kotlinx.android.synthetic.main.activity_admin_login.*


class AdminLogin : AppCompatActivity() {
    // FirebaseDatabase variables declaration
    var database: FirebaseDatabase? = null
    private var adminRef: DatabaseReference? = null
    private var studentRef: DatabaseReference? = null
    var TAG = "AdminLogin"
    var from:String? = null
    var admins:MutableList<AdminUser> = arrayListOf()
    var students:MutableList<Student> = arrayListOf()
    var smartCardSharedPreferences:SmartCareSharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        from = intent.getStringExtra("from")
        smartCardSharedPreferences = SmartCareSharedPreferences(this,"profileInfo")
        database = FirebaseDatabase.getInstance("https://smart-id-card-f2eab-default-rtdb.firebaseio.com/")
        adminRef = database!!.getReference("admins")
        studentRef = database!!.getReference("students")
    }
    fun onClickLogin(view: View) {
        disableAllHelperTexts()
        var username = editTextUserName.text.toString()
        var password = editTextPassword.text.toString()
        if(isAllFieldsValid(username,password)) {
           loginAdmin()
        }
    }
     fun loginAdmin(){
        var username = editTextUserName.text.toString()
        var password = editTextPassword.text.toString()
        var isUserExist = false
        var user:AdminUser? = null
        var student:Student? = null
        admins.clear()
        if(from.equals("student")){
            studentRef!!.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    students.clear()
                    for(it in dataSnapshot.children){
                        students.add(it.getValue(Student::class.java)!!)
                    }

                    for (it in students){
                        if(it.username.equals(username)){
                            student = it
                            isUserExist = true
                            break;
                        }
                    }

                    if(isUserExist){
                        Log.i(TAG,"student: "+student)
                        if(password.equals(student?.password)){
                            Intent(this@AdminLogin, FingerPrintScan::class.java).also {
                                it.putExtra("from","student")
                                it.putExtra("data",Gson().toJson(student))
                                startActivity(it)
                            }
                        }else{
                            textInput_password.helperText = "Password doesn't match"

                        }
                    }else{
                        textInput_username.helperText = "Student not exist"
                    }



                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }else{
            adminRef!!.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    dataSnapshot.children.forEach {
                        admins.add(it.getValue(AdminUser::class.java)!!)
                    }

                    for (it in admins){
                        if(it.username.equals(username)){
                            user = it
                            isUserExist = true
                            break;
                        }
                    }

                    if(isUserExist){
                        if(password.equals(user?.password)){
                            smartCardSharedPreferences?.saveBooleanValue("login",true)
                            Intent(this@AdminLogin, UserOperations::class.java).also {
                                startActivity(it)
                                finish()
                                finishAffinity()
                            }
                        }else{
                            textInput_password.helperText = "Password doesn't match"

                        }
                    }else{
                        textInput_username.helperText = "User not exist"
                    }



                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }

    }

     fun isAllFieldsValid(username:String,password:String):Boolean{
        var status = true;
        if(username.isEmpty()){
            textInput_username.helperText = "Cannot be empty"
            status = false
        }
        if(password.isEmpty()){
            textInput_password.helperText = "Cannot be empty"
            status = false
        }
        return status
    }
    fun disableAllHelperTexts(){
        textInput_password.isHelperTextEnabled = false
        textInput_username.isHelperTextEnabled = false
    }

}