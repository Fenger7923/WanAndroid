package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.ui.main.MainPage


/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MainPage()
        }
    }

    override fun initView() {
    }
}