package com.example.spendy.Dao

import android.content.ContentValues
import com.example.spendy.AccountType
import com.example.spendy.DBHelper

class AccountTypeDao {

    fun insert(vt: DBHelper, AccountId:Int, AccountType:String){
        val db=vt.writableDatabase
        val values=ContentValues()
        values.put("AccountId",AccountId)
        values.put("AccountType",AccountType)

        db.insertOrThrow("accounttype",null,values)
        db.close()
    }

    fun select(vt: DBHelper):ArrayList<AccountType>{
        val types=ArrayList<AccountType>()
        val db=vt.writableDatabase

        val cursor=db.rawQuery("Select * From accounttype",null)

        while(cursor.moveToNext()){
            val type= AccountType(cursor.getInt(cursor.getColumnIndex("AccountId")),
                    cursor.getString(cursor.getColumnIndex("AccountType")))

            types.add(type)

        }
        return types
    }
}