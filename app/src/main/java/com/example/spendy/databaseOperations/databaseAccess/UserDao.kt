package com.example.spendy.databaseOperations.databaseAccess

import android.content.ContentValues
import com.example.spendy.DBHelper
import com.example.spendy.User

class UserDao {
    //User tablosundaki işlemler için

    //Insert
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


    //Delete
    fun delete(vt: DBHelper, AccountId:Int){
        val db=vt.writableDatabase
        db.delete("user","AccountId=?", arrayOf(AccountId.toString()))
        db.close()


    }

    //Get Users
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

    //Get User Id
    fun getUserID(dbHelper:DBHelper,email:String):Int{

        val db=dbHelper.writableDatabase

        val cursor=db.rawQuery("Select AccountId From user Where Email= $email ",null)


        return cursor.getInt(cursor.getColumnIndex("AccountId"))
    }

    //Get User
    fun getUser(dbHelper:DBHelper,id:Int):User{

        val db=dbHelper.writableDatabase

         val user = User(0,"","","","")

        val list = getUsers(dbHelper)

        list.forEach {

            if(it.AccountId==id){
                return it
            }
        }

        return user

    }

}