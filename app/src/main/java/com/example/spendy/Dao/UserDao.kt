package com.example.spendy.Dao

import android.content.ContentValues
import com.example.spendy.DBHelper
import com.example.spendy.User

class UserDao {
    //User tablosundaki işlemler için
    fun insert(vt: DBHelper,user:User){
        val db=vt.writableDatabase
        val values=ContentValues()


        values.put("Name",user.Name)
        values.put("Surname",user.Surname)
        values.put("EMail",user.EMail)
        values.put("Password",user.Password)

        db.insertOrThrow("user",null,values)
        db.close()
    }

    fun delete(vt: DBHelper, AccountId:Int){
        val db=vt.writableDatabase
        db.delete("user","AccountId=?", arrayOf(AccountId.toString()))
        db.close()
    }


    fun getUsers(vt: DBHelper):ArrayList<User>{

        val userlist=ArrayList<User>()
        val db=vt.writableDatabase

        val cursor=db.rawQuery("Select * From user ",null)
        while(cursor.moveToNext()){
            val user= User(cursor.getInt(cursor.getColumnIndex("AccountId")),
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Surname")),
                        cursor.getString(cursor.getColumnIndex("EMail")),
                        cursor.getString(cursor.getColumnIndex("Password"))
                        )
            userlist.add(user)
        }
        return userlist
    }
}