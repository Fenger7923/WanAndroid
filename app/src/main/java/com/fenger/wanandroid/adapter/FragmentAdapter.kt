package com.fenger.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author fengerzhang
 * @date 2/1/21 9:28 PM
 */
class FragmentAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]
}