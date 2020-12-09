package com.example.spendy.Homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.spendy.ExpenseIncome.FragmentExpenseIncome
import com.example.spendy.R
import com.github.mikephil.charting.data.Entry
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.nav_header.view.*


private lateinit var activitiesList:ArrayList<AccountActivity>

private lateinit var adapter: HomePageRVAdapter

private lateinit var studentList:ArrayList<Entry>
private lateinit var yearsList:ArrayList<String>


class HomepageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)



        // setActivitiesAdapter()

        setToolbar()
        setDrawer()


        supportFragmentManager.beginTransaction().add(R.id.fragmentHolder, FragmentHomepage()).commit()
        //setActivitiesAdapter()

        onPressedMenuItems()
        setNavigationHeader()

        //onPressedNavTV()



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

    //


    //OnPressed Menu Items
    fun onPressedMenuItems(){

        navigationView.setNavigationItemSelectedListener {menuItem ->

            if(menuItem.itemId == R.id.action_budget_manager){

                Toast.makeText(applicationContext,"Budget Manager", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, FragmentExpenseIncome()).commit()
            }
            if(menuItem.itemId == R.id.action_homepage){

                Toast.makeText(applicationContext,"Homepage", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.fragmentHolder, FragmentHomepage()).commit()
            }
            if(menuItem.itemId == R.id.action_settings){

                Toast.makeText(applicationContext,"Settings", Toast.LENGTH_SHORT).show()
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



}