package com.farhanfr.trackcovid19.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.farhanfr.trackcovid19.ui.home.GlobalHomeFragment
import com.farhanfr.trackcovid19.ui.home.LocalHomeFragment

class HomePager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val pageList = listOf(LocalHomeFragment(), GlobalHomeFragment())

    override fun getItem(position: Int): Fragment {
        return pageList[position]
    }

    override fun getCount(): Int {
        return pageList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Indonesia"
            1 -> "Global"
            else -> "Third Tab"
        }
    }
}