package com.example.spendy.databaseOperations.databaseAccess

import android.content.ContentValues
import com.example.spendy.*
import com.example.spendy.databaseOperations.databaseEntities.Account

class AccountDao {

    //Account tablosundaki işlemler için

    //Insert
    fun insert(vt: DBHelper, account:Account){

        val db=vt.writableDatabase

        val values=ContentValues()
        values.put("AccountId",account.AccountId.toString())//?
        values.put("AccountType",account.AccountType.toString())
        values.put("Language",account.Language.toString())

        db.insertOrThrow("account",null,values)
        db.close()
    }

    //Select
    fun select(vt: DBHelper, AccountId: Int):ArrayList<Account>{



        val accountList=ArrayList<Account>()
        val db=vt.writableDatabase

        val cursor=db.rawQuery("Select * From user,account,language,accounttype " +
                "WHERE user.AccountId=account.AccountId and account.AccountType=accounttype.AccountId " +
                "and account.Language=language.LanguageId and AccountId= $AccountId",null)


        while(cursor.moveToNext()){
            val user= User(cursor.getInt(cursor.getColumnIndex("AccountId")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Surname")),
                    cursor.getString(cursor.getColumnIndex("EMail")),
                    cursor.getString(cursor.getColumnIndex("Password"))
            )

            val language= Language(cursor.getInt(cursor.getColumnIndex("LanguageId")),
            cursor.getString(cursor.getColumnIndex("Language"))
            )

            val type= AccountType(cursor.getInt((cursor.getColumnIndex("AccountId"))),
            cursor.getString(cursor.getColumnIndex("AccountTye"))
            )

            val account= Account(user, type, language)

            accountList.add(account)
        }



        return accountList

    }




}