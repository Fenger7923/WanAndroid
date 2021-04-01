package com.fenger.wanandroid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.FragmentAdapter
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.fragment.LoginFragment
import com.fenger.wanandroid.ui.fragment.RegisterFragment
import kotlinx.android.synthetic.main.activity_login.login_viewpager

/**
 * @author fengerzhang
 * @date 3/19/21 10:25 AM
 */
class LoginActivity : BaseActivity() {

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }

    override fun setLayoutId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val fragments = listOf(LoginFragment(), RegisterFragment())
        login_viewpager.adapter = FragmentAdapter(supportFragmentManager, fragments)
        changeToLogin()
    }

    fun changeToRegister() {
        login_viewpager.currentItem = 1
    }

    fun changeToLogin() {
        login_viewpager.currentItem = 0
    }
}