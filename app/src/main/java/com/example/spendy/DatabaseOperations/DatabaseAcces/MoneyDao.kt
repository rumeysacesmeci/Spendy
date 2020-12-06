package com.example.spendy.DatabaseOperations.DatabaseAcces

import android.content.ContentValues
import com.example.spendy.DBHelper
import com.example.spendy.Money
import com.example.spendy.User

class MoneyDao {
    fun insert(vt: DBHelper, money:Money){
        val db=vt.writableDatabase
        val values=ContentValues()

        values.put("MoneyId",money.MoneyId)
        values.put("AccountId",money.AccountId.toString())//?
        values.put("Income",money.Income)
        values.put("Expense",money.Expense)
        values.put("Goal",money.Goal)

        db.insertOrThrow("money",null,values)
        db.close()
    }
    fun select(vt: DBHelper, AccountId: Int):ArrayList<Money>{
        val db=vt.writableDatabase
        val moneys=ArrayList<Money>()

        val cursor=db.rawQuery("Select * From money,user Where money.AccountId=user.AccountId " +
                "and AccountID= $AccountId",null)
        while(cursor.moveToNext()){
            val user= User(cursor.getInt(cursor.getColumnIndex("AccountId")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Surname")),
                    cursor.getString(cursor.getColumnIndex("EMail")),
                    cursor.getString(cursor.getColumnIndex("Password"))
            )
            val money= Money(cursor.getInt(cursor.getColumnIndex("MoneyId")),
                    cursor.getInt(cursor.getColumnIndex("Income")),
                    cursor.getInt(cursor.getColumnIndex("Expense")),
                    cursor.getInt(cursor.getColumnIndex("Goal")),user
                    )
            moneys.add(money)
        }
        return moneys
    }
}