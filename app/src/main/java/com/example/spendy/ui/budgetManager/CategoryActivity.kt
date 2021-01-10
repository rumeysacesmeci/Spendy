package com.example.spendy.ui.budgetManager


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.R
import com.example.spendy.adapters.CategoryAdapter
import com.example.spendy.models.Budget
import com.example.spendy.models.Categories
import com.example.spendy.repository.Repository
import kotlinx.android.synthetic.main.activity_category.*

import kotlin.collections.ArrayList


class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    private val repository = Repository()

    private lateinit var categoriesArrayList: ArrayList<Categories>

    private lateinit var adapter: CategoryAdapter


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


}