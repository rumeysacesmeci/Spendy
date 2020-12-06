package com.example.spendy.DatabaseOperations.DatabaseAcces

import android.content.ContentValues
import com.example.spendy.AccountType
import com.example.spendy.DBHelper

class AccountTypeDao {


    //Insert
    fun insert(vt: DBHelper, accountType:AccountType){
        val db=vt.writableDatabase
        val values=ContentValues()
        values.put("AccountId",accountType.AccountId.toString())
        values.put("AccountType",accountType.AccountType.toString())

        db.insertOrThrow("accounttype",null,values)
        db.close()
    }

    //Select
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