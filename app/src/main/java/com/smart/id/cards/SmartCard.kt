package com.smart.id.cards

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.smart.id.cards.data.Student
import kotlinx.android.synthetic.main.activity_smart_card.*

class SmartCard : AppCompatActivity() {
    var student:Student ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_card)
        student = Gson().fromJson(intent.getStringExtra("data"),Student::class.java)
        var qrString = student?.firstName+"+"+student?.lastName+"+"+student?.geder+"+"+student?.username+"+"+student?.emiratesID
        var qrCodeBitmap = getQrCodeBitmap(qrString)
        iv_qr_image.setImageBitmap(qrCodeBitmap)
        tv_name.text = student?.firstName+" "+student?.lastName
        tv_emirates_id.text = "Emirates ID: "+student?.emiratesID
        tv_username.text = "Username: "+student?.username
        tv_type.text = "Type: "+student?.userType

    }

    fun getQrCodeBitmap(code:String): Bitmap {
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 } // Make the QR code buffer border narrower
        val size = 512 //pixels
        val bits = QRCodeWriter().encode(code, BarcodeFormat.QR_CODE, size , size, hints)

        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLUE else Color.WHITE)
                }
            }
        }
    }
}