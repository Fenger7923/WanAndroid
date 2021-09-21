package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.library_base.http.login
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.ui.activity.LoginActivity
import com.fenger.wanandroid.utils.User
import kotlinx.android.synthetic.main.fragment_login.ll_go_register
import kotlinx.android.synthetic.main.fragment_login.piv_login_account
import kotlinx.android.synthetic.main.fragment_login.piv_login_password
import kotlinx.android.synthetic.main.fragment_login.sv_login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun initView() {
        ll_go_register.setOnClickListener {
            (activity as LoginActivity).changeToRegister()
        }

        sv_login.setOnClickListener {
            val username = piv_login_account.text.toString()
            val password = piv_login_password.text.toString()
            CoroutineScope(Dispatchers.Default).launch {
                User.setUser(login(username, password))
                activity?.finish()
            }
        }
    }

}
