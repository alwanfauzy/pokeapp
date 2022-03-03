package com.alwan.pokeapp.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = StatsFragment()
            1 -> fragment = DetailFragment()
        }

        return fragment as Fragment
    }

    override fun getItemCount() = 2
}