package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_register.ll_go_login

/**
 * @author fengerzhang
 * @date 3/19/21 11:17 AM
 */
class RegisterFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  = inflater.inflate(R.layout.fragment_register, container, false)

    override fun initView() {
        ll_go_login.setOnClickListener {
            (activity as LoginActivity).changeToLogin()
        }
    }
}