package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.ui.tree.TreePage
import com.fenger.wanandroid.ui.tree.TreeViewModel


/**
 * @author fengerzhang
 * @date 3/5/21 4:10 PM
 */
class TreeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TreePage()
        }
    }

    override fun initView() {

    }
}