package com.example.spendy.SignUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.DBHelper
import com.example.spendy.Dao.UserDao
import com.example.spendy.R
import com.example.spendy.User
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_sign_up.*


class ActivitySignUp : AppCompatActivity(){

    //Initiliaze
    private val userDao = UserDao()
    private val dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //Copy Db
        dbCopy()

        //SignUp
        signUp()

    }

    //Sign Up
    fun signUp(){

        btnLogin.setOnClickListener {

            userDao.insert(dbHelper,getSignUpValues())

        }
    }

    //Get Sign Up Values
    fun getSignUpValues():User{

        val name = txtName.text.toString();
        val surname = txtName.text.toString();
        val email = txtName.text.toString();
        val password = txtName.text.toString();

        val user=User(0,name,surname,email,password)

        return user

    }

    //DB Copy
    fun dbCopy(){

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