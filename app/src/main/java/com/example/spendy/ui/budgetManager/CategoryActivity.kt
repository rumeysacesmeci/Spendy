package com.example.spendy.ui.budgetManager

import android.annotation.SuppressLint
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_category.*
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CategoryActivity : AppCompatActivity(),CategoryAdapter.OnItemClickListener {
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

        toolbarCreate()




        categoryAdder()

    }





    // Add categories to recyclerview
    fun categoryAdder(){
        val c1 = Categories("No Category")
        val c2 = Categories("BOOK")
        val c3 = Categories("CAR")
        val c4 = Categories("CHİLD")
        val c5 = Categories("CLOTHES")
        val c6 = Categories("COSMETIC")
        val c7 = Categories("CREDIT CARD")
        val c8 = Categories("DONATION")
        val c9 = Categories("DUES")
        val c10 = Categories("EDUCATION")
        val c11 = Categories("ELECTRICITY")
        val c12 = Categories("ENTERTAINMENT")
        val c13 = Categories("FOOD")
        val c14 = Categories("HEALTH")
        val c15 = Categories("HEAT")
        val c16 = Categories("HOUSE")
        val c17 = Categories("INSURANCE")
        val c18 = Categories("INTERNET")
        val c19 = Categories("INVOICE")
        val c20 = Categories("MARKET")
        val c21 = Categories("PET")
        val c22 = Categories("RENTAL FEE")
        val c23 = Categories("SALARY")
        val c24 = Categories("SALES")
        val c25 = Categories("TELEPHONE")
        val c26 = Categories("TRANSPORTATION")
        val c27 = Categories("TRAVEL")
        val c28 = Categories("TV")
        val c29 = Categories("WATER")

        categoriesArrayList = ArrayList<Categories>()
        categoriesArrayList.add(c1)
        categoriesArrayList.add(c2)
        categoriesArrayList.add(c3)
        categoriesArrayList.add(c4)
        categoriesArrayList.add(c5)
        categoriesArrayList.add(c6)
        categoriesArrayList.add(c7)
        categoriesArrayList.add(c8)
        categoriesArrayList.add(c9)
        categoriesArrayList.add(c10)
        categoriesArrayList.add(c11)
        categoriesArrayList.add(c12)
        categoriesArrayList.add(c13)
        categoriesArrayList.add(c14)
        categoriesArrayList.add(c15)
        categoriesArrayList.add(c16)
        categoriesArrayList.add(c17)
        categoriesArrayList.add(c18)
        categoriesArrayList.add(c19)
        categoriesArrayList.add(c20)
        categoriesArrayList.add(c21)
        categoriesArrayList.add(c22)
        categoriesArrayList.add(c23)
        categoriesArrayList.add(c24)
        categoriesArrayList.add(c25)
        categoriesArrayList.add(c26)
        categoriesArrayList.add(c27)
        categoriesArrayList.add(c28)
        categoriesArrayList.add(c29)


        adapter = CategoryAdapter(this@CategoryActivity,categoriesArrayList,this)
        rvCategories.adapter = adapter
    }


    //Click method for category recyclerview(every row)

    @SuppressLint("NewApi")
    override fun onItemClick(position: Int) {
        val clickedItem = categoriesArrayList[position]
        val messageType = intent.getIntExtra("type",0)
        val messageAmount = intent.getDoubleExtra("amount",0.0)
        val messageTime = intent.getStringExtra("time")

        /*val pattern = "dd-MM-yyyy HH:mm"

        val formatter = DateTimeFormatter.ofPattern(pattern)
        val localDateTime = LocalDateTime.from(formatter.parse(messageTime))
        var ts = Timestamp(localDateTime*/

        //var ts = Timestamp(messageTime.toLong(),0)

        val budget = Budget(messageType,messageAmount,clickedItem.categoryName, messageTime)

        repository.addIncome(budget)
        adapter.notifyItemChanged(position)
        finish()

    }



    // Create toolbar for category page
    fun toolbarCreate(){
        toolbarCategory.title = "SELECT CATEGORY"
        toolbarCategory.setLogo(R.drawable.category)
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