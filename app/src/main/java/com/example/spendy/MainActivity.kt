package com.example.spendy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.spendy.Dao.UserDao
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbcopy()

        val aa=DBHelper(this)
        //UserDao().insert(aa,1,"Resul","Ekinci","resulekinci10@gmail.com",123456)

        var liste=ArrayList<User>()
        val vt=DBHelper(this)
        liste= UserDao().Users(vt)
        deneme.text=liste[0].Name
        Log.e("aaa",liste.size.toString())
    }

    fun dbcopy(){

        val db=DatabaseCopyHelper(this)

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