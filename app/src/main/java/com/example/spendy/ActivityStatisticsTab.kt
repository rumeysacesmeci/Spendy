package com.example.spendy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.ui.statistics.FragmentExpense
import com.example.spendy.ui.statistics.FragmentIncome
import com.example.spendy.ui.statistics.MyViewPagerAdapter
import kotlinx.android.synthetic.main.activity_statistics_tab.*


class ActivityStatisticsTab : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics_tab)

        //ViewPager için adapter nesnesi oluşturuyoruz ve kullanacağımız fragment, title'ları ekliyoruz.
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentExpense(), "Expense")
        adapter.addFragment(FragmentIncome(), "Income")



        //Adapter'ımızdaki verileri viewPager adapter'a veriyoruz
        viewPager2.adapter = adapter
        //Tablar arasında yani viewPager'lar arasında geçisi sağlıyoruz
        tabLayout.setupWithViewPager(viewPager2)

    }

}