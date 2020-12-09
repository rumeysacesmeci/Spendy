package com.example.spendy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.Dao.UserDao
import com.example.spendy.Homepage.HomepageActivity
import com.example.spendy.SignUp.ActivitySignUp
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        dbcopy()
        val email = txtEmail.text
        val password = txtPassword.text
        val nvgToSignUp = Intent(this@SignIn,ActivitySignUp::class.java )
        val nvgToHomePage = Intent(this@SignIn, HomepageActivity::class.java)
        val signUp = tvSignUp
        //UserDao().insert(aa,1,"Resul","Ekinci","resulekinci10@gmail.com",123456)

        var liste: ArrayList<User>
        val vt=DBHelper(this)
        liste= UserDao().getUsers(vt)

        val signIn = btnSignIn
        signIn.setOnClickListener{
            for (user in liste){
                if(user.EMail == email.toString() && user.Password == password.toString()){
                    Toast.makeText(applicationContext,"Giriş başarılı",Toast.LENGTH_LONG).show()
                    startActivity(nvgToHomePage)
                }
            }
        }

        signUp.setOnClickListener{
            startActivity(nvgToSignUp)
        }
    }

    fun dbcopy(){

        val db= DatabaseCopyHelper(this)

        try{
            db.createDataBase()
        }catch(e:Exception){
            e.printStackTrace()
        }

        try{
            db.openDataBase()
        }catch(e:Exception){
            e.printStackTrace()
        }


    }
}