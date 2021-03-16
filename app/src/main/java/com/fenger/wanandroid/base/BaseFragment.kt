package com.fenger.wanandroid.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * @author fengerzhang
 * @date 2/1/21 9:25 PM
 */
abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    abstract fun initView()
}