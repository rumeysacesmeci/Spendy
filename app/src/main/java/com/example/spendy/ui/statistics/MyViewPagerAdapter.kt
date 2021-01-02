package com.example.spendy.ui.statistics

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<String> = ArrayList()

    //Fragment position
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
    //Fragment count
    override fun getCount(): Int {
        return fragmentList.size
    }
    //Add fragments and titles
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }
    //Title position
    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}