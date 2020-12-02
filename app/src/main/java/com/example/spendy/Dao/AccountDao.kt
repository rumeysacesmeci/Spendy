package com.example.spendy.Dao

import android.content.ContentValues
import com.example.spendy.*
import com.example.spendy.DB.Account

class AccountDao {
    //Account tablosundaki işlemler için
    fun insert(vt: DBHelper, AccountId:Int, AccountType:String, Language:String){
        val db=vt.writableDatabase
        val values=ContentValues()
        values.put("AccountId",AccountId)
        values.put("AccountType",AccountType)
        values.put("Language",Language)

        db.insertOrThrow("account",null,values)
        db.close()
    }
    fun select(vt: DBHelper, AccountId: Int):ArrayList<Account>{

        val AccountList=ArrayList<Account>()
        val db=vt.writableDatabase

        val cursor=db.rawQuery("Select * From user,account,language,accounttype " +
                "WHERE user.AccountId=account.AccountId and account.AccountType=accounttype.AccountId " +
                "and account.Language=language.LanguageId and AccountId= $AccountId",null)


        while(cursor.moveToNext()){
            val user= User(cursor.getInt(cursor.getColumnIndex("AccountId")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Surname")),
                    cursor.getString(cursor.getColumnIndex("EMail")),
                    cursor.getInt(cursor.getColumnIndex("Password"))
            )

            val language= Language(cursor.getInt(cursor.getColumnIndex("LanguageId")),
            cursor.getString(cursor.getColumnIndex("Language"))
            )

            val type= AccountType(cursor.getInt((cursor.getColumnIndex("AccountId"))),
            cursor.getString(cursor.getColumnIndex("AccountTye"))
            )

            val account= Account(user,type,language)

            AccountList.add(account)
        }



        return AccountList

    }
}