package com.smart.id.cards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.smart.id.cards.utils.SmartCareSharedPreferences

class UserOperations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_operations)
    }

    fun onClickAddUserProfile(view: View) {
        Intent(this,AddUser::class.java).also {
            startActivity(it)
        }
    }
    fun onClickEditUserProfile(view: View) {
        Intent(this,EditUserUsername::class.java).also {
            startActivity(it)
        }
    }
    fun onClickDeleteUserProfile(view: View) {
        Intent(this,DeleteUser::class.java).also {
            startActivity(it)
        }
    }

    fun onClickLogout(view: View) {
        showDialog()
    }
    fun showDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Do you really want to logout?")
        builder.setPositiveButton("Yes") { dialog, which ->
            SmartCareSharedPreferences(this,"profileInfo").saveBooleanValue("login",false)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            finishAffinity()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
}