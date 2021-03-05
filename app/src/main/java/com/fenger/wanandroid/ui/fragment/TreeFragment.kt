package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment

/**
 * @author fengerzhang
 * @date 3/5/21 4:10 PM
 */
class TreeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}