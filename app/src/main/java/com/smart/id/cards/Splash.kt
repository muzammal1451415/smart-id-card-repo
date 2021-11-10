package com.smart.id.cards
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.smart.id.cards.utils.SmartCareSharedPreferences

class Splash : AppCompatActivity() {
    var smartCareSharedPreferences:SmartCareSharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        smartCareSharedPreferences = SmartCareSharedPreferences(this,"profileInfo")
        // added comments
        Handler().postDelayed({
            var isUserLoggedInByAdmin = smartCareSharedPreferences?.getBooleanValue("login")
            if(isUserLoggedInByAdmin!!){
                Intent(this,UserOperations::class.java).also {
                    startActivity(it)
                    finish()
                    finishAffinity()
                }
            }else {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                    finishAffinity()
                }
            }

        },2000L)
    }
}