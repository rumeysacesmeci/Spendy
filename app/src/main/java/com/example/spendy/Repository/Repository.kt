package com.example.spendy.Repository

import android.accounts.Account
import android.content.Context
import com.example.spendy.*
import com.example.spendy.DatabaseOperations.DatabaseAcces.*

class Repository {


    private val userDao = UserDao()
    private  val accountDao = AccountDao()
    private  val accountTypeDao = AccountTypeDao()
    private  val languageDao = LanguageDao()
    private  val moneyDao = MoneyDao()
    private lateinit var dbHelper: DBHelper



    //User Operations

    //Add User

     fun addUser(context:Context,user:User){

        dbHelper =DBHelper(context)

        userDao.insert(dbHelper,user)

    }

    //Delete
    fun deleteUser(context:Context,email:String){

        dbHelper =DBHelper(context)

        userDao.delete(dbHelper,userDao.getUserID(dbHelper,email))


    }


    //Get Users
    fun getUsers(context:Context):ArrayList<User>{

        dbHelper = DBHelper(context)

        var list = ArrayList<User>()

        list = userDao.getUsers(dbHelper)

        return list

    }


    //Account Operations

    //Add Account

    fun addAccount(context:Context,account:com.example.spendy.DatabaseOperations.DatabaseEntitates.Account){

        dbHelper =DBHelper(context)

        accountDao.insert(dbHelper,account)

    }

    //Get Accounts
    fun getAccount(context:Context,email: String):ArrayList<com.example.spendy.DatabaseOperations.DatabaseEntitates.Account>{

        dbHelper = DBHelper(context)

        val id = userDao.getUserID(dbHelper,email)

        var list = ArrayList<com.example.spendy.DatabaseOperations.DatabaseEntitates.Account>()

        list = accountDao.select(dbHelper,id)

        return list

    }

    //Money Operations

    //Add Money

    fun addMoney(context:Context,money:Money){

        dbHelper =DBHelper(context)

        moneyDao.insert(dbHelper,money)

    }

    //Get Money
    fun getMoney(context:Context,email: String):ArrayList<Money>{

        dbHelper = DBHelper(context)

        val id = userDao.getUserID(dbHelper,email)

        var list = ArrayList<Money>()

        list = moneyDao.select(dbHelper,id)

        return list

    }


    //Language Operations

    //Add Language

    fun addLanguage(context:Context,language:Language){

        dbHelper =DBHelper(context)

        languageDao.insert(dbHelper,language)

    }

    //Get Users
    fun getLanguage(context:Context):ArrayList<Language>{

        dbHelper = DBHelper(context)

        var list = ArrayList<Language>()

        list = languageDao.select(dbHelper)

        return list

    }

    //AccountType Operations

    //Add Account Type

    fun addAccountType(context:Context,accountType:AccountType){

        dbHelper =DBHelper(context)

        accountTypeDao.insert(dbHelper,accountType)

    }

    //Get Users
    fun getAccountType(context:Context):ArrayList<AccountType>{

        dbHelper = DBHelper(context)

        var list = ArrayList<AccountType>()

        list = accountTypeDao.select(dbHelper)

        return list

    }

}