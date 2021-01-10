package com.example.spendy.ui.budgetManager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.R
import com.example.spendy.adapters.CategoryAdapter
import com.example.spendy.models.Budget
import com.example.spendy.models.Categories
import com.example.spendy.repository.Repository
import com.example.spendy.ui.homepage.HomepageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.card_view_category.*
import kotlinx.android.synthetic.main.card_view_category.view.*
import kotlinx.android.synthetic.main.fragment_expense_income.*
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {
    private val repository = Repository()
    private lateinit var categoriesArrayList: ArrayList<Categories>
    private lateinit var adapter: CategoryAdapter
    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(this)





        categoryAdder()

    }


    // Add categories to recyclerview
    private fun categoryAdder() {


        categoriesArrayList = ArrayList<Categories>()

        categoriesArrayList.add(Categories(getString(R.string.no_category)))
        categoriesArrayList.add(Categories(getString(R.string.book)))
        categoriesArrayList.add(Categories(getString(R.string.car)))
        categoriesArrayList.add(Categories(getString(R.string.child)))
        categoriesArrayList.add(Categories(getString(R.string.clothes)))
        categoriesArrayList.add(Categories(getString(R.string.cosmetic)))
        categoriesArrayList.add(Categories(getString(R.string.credit_card)))
        categoriesArrayList.add(Categories(getString(R.string.donation)))
        categoriesArrayList.add(Categories(getString(R.string.dues)))
        categoriesArrayList.add(Categories(getString(R.string.education)))
        categoriesArrayList.add(Categories(getString(R.string.electricity)))
        categoriesArrayList.add(Categories(getString(R.string.entertainment)))
        categoriesArrayList.add(Categories(getString(R.string.food)))
        categoriesArrayList.add(Categories(getString(R.string.health)))
        categoriesArrayList.add(Categories(getString(R.string.heat)))
        categoriesArrayList.add(Categories(getString(R.string.house)))
        categoriesArrayList.add(Categories(getString(R.string.insurance)))
        categoriesArrayList.add(Categories(getString(R.string.internet)))
        categoriesArrayList.add(Categories(getString(R.string.invoice)))
        categoriesArrayList.add(Categories(getString(R.string.market)))
        categoriesArrayList.add(Categories(getString(R.string.pet)))
        categoriesArrayList.add(Categories(getString(R.string.rental_fee)))
        categoriesArrayList.add(Categories(getString(R.string.salary)))
        categoriesArrayList.add(Categories(getString(R.string.sales)))
        categoriesArrayList.add(Categories(getString(R.string.telephone)))
        categoriesArrayList.add(Categories(getString(R.string.transportation)))
        categoriesArrayList.add(Categories(getString(R.string.travel)))
        categoriesArrayList.add(Categories(getString(R.string.tv)))
        categoriesArrayList.add(Categories(getString(R.string.water)))



        adapter = CategoryAdapter(this@CategoryActivity, categoriesArrayList, this)
        rvCategories.adapter = adapter
    }


    //Click method for category recyclerview(every row)

    override fun onItemClick(position: Int) {
        val clickedItem = categoriesArrayList[position]
        val messageType = intent.getIntExtra("type", 0)
        val messageAmount = intent.getDoubleExtra("amount", 0.0)



        val budget = Budget(messageType, messageAmount, clickedItem.categoryName, "messageTime")

        repository.addIncome(budget)
        adapter.notifyItemChanged(position)
        finish()

    }


    // Create toolbar for category page
    fun toolbarCreate() {



        toolbarCategory.setLogo(R.drawable.category)
    }

    //Set Language
    private fun setLocate(language:String?){


        var locale = Locale(language)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale



        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()

        editor.putString("MyLang",language)

        editor.apply()



    }


    //Load Language
    fun loadLocate(){

        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)

        val language = sharedPreferences.getString("MyLang","")

        setLocate(language)

    }
    /*@SuppressLint("NewApi")
    fun stringToTimeStamp():Timestamp{
        val messageTime = intent.getStringExtra("time")




       /* val l = LocalDate.parse("12-10-2020 17:25", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))

        val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond*/
        val pattern = "dd-MM-yyyy HH:mm"

        val formatter = DateTimeFormatter.ofPattern(pattern)
        val localDateTime = LocalDateTime.from(formatter.parse(messageTime))
       com.google.firebase.Timestamp.CREATOR.createFromParcel()

        return  timeStamp
    }*/

}