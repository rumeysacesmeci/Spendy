package com.example.spendy

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.nav_header.view.*


class MainActivity : AppCompatActivity() {


    private lateinit var activitiesList:ArrayList<AccountActivity>

    private lateinit var adapter: HomePageRVAdapter

    private lateinit var studentList:ArrayList<Entry>
    private lateinit var yearsList:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       // setActivitiesAdapter()

        setToolbar()
        setDrawer()


        supportFragmentManager.beginTransaction().add(R.id.fragmentHolder,FragmentHomepage()).commit()
        //setActivitiesAdapter()
        setNavigationHeader()
        onPressedMenuItems()





    }

    //Back Button Pressed
    override fun onBackPressed() {


        if(drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START)
        }
        else{

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    //OnPressed Menu Items
    fun onPressedMenuItems(){

        navigationView.setNavigationItemSelectedListener {menuItem ->

            if(menuItem.itemId ==R.id.action_income){

                Toast.makeText(applicationContext,"Income",Toast.LENGTH_SHORT).show()
            }
            if(menuItem.itemId ==R.id.action_expense){

                Toast.makeText(applicationContext,"Expense",Toast.LENGTH_SHORT).show()

            }
            if(menuItem.itemId ==R.id.action_settings){

                Toast.makeText(applicationContext,"Settings",Toast.LENGTH_SHORT).show()
            }


            drawer.closeDrawer(GravityCompat.START)


            true
        }

    }

    fun setNavItemColor(){


    }
    //Set Navigation Header
    fun setNavigationHeader(){

        val header=navigationView.inflateHeaderView(R.layout.nav_header)
        header.tvNavHeader.text="Spendy"
    }
    //Set Drawer
    fun setDrawer(){

        val toggle = ActionBarDrawerToggle(this,drawer,toolbar,0,0)
        drawer.addDrawerListener(toggle)

        toggle.syncState()

    }


    //Set Toolbar
    fun setToolbar(){

        toolbar.title="Homepage"
        setSupportActionBar(toolbar)
    }


    //Filling RV & Driver/Test Code
    fun setActivitiesAdapter(){
        rvHomepage.setHasFixedSize(true)
        rvHomepage.layoutManager = LinearLayoutManager(this)

        val act1 = AccountActivity(1,211)
        val act2 = AccountActivity(1,111)
        val act3 = AccountActivity(0,241)
        val act4 = AccountActivity(0,211)
        val act5= AccountActivity(1,111)
        val act6= AccountActivity(0,111)

        activitiesList = ArrayList<AccountActivity>()

        activitiesList.add(act1)
        activitiesList.add(act2)
        activitiesList.add(act3)
        activitiesList.add(act4)
        activitiesList.add(act5)
        activitiesList.add(act6)

        adapter = HomePageRVAdapter(this,activitiesList)

        rvHomepage.adapter=adapter

    }



}