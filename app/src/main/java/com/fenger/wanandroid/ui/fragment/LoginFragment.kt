package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.network.RetrofitHelper
import com.fenger.wanandroid.ui.activity.LoginActivity
import com.fenger.wanandroid.utils.User
import kotlinx.android.synthetic.main.fragment_login.ll_go_register
import kotlinx.android.synthetic.main.fragment_login.piv_login_account
import kotlinx.android.synthetic.main.fragment_login.piv_login_password
import kotlinx.android.synthetic.main.fragment_login.sv_login
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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
            RetrofitHelper.retrofitService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode != 0) {
                        Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
                    } else {
                        User.setUser(it)
                        activity?.finish()
                    }
                }, {
                    it.toString()
                })
        }
    }

}
