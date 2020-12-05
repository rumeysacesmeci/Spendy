package com.example.spendy.DB

import com.example.spendy.AccountType
import com.example.spendy.Language
import com.example.spendy.User

data class Account(var AccountId: User, var AccountType: AccountType,
                   var Language: Language) {


}