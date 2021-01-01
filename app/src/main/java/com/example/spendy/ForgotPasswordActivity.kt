package com.example.spendy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    var auth = FirebaseAuth.getInstance()


    fun resetPassword(view: View){
        val emailAdress = txtEmail.text.toString()

        auth.sendPasswordResetEmail(emailAdress).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Email couldn't send.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}