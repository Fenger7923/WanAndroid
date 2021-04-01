package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.utils.User
import kotlinx.android.synthetic.main.fragment_mine.ll_user_id
import kotlinx.android.synthetic.main.fragment_mine.ll_user_level_ranking
import kotlinx.android.synthetic.main.fragment_mine.rl_user_info
import kotlinx.android.synthetic.main.fragment_mine.tv_user_id
import kotlinx.android.synthetic.main.fragment_mine.tv_user_level
import kotlinx.android.synthetic.main.fragment_mine.tv_user_name
import kotlinx.android.synthetic.main.fragment_mine.tv_user_ranking

/**
 * @author fengerzhang
 * @date 2/1/21 9:35 PM
 */
class MyFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onResume() {
        super.onResume()
        initView()
    }
    override fun initView() {
        if (!User.isLogin) {
            setLogoutState()
        } else {
            setLoginState()
        }
    }

    private fun setLogoutState() {
        rl_user_info.setOnClickListener {
            User.doIfLogin(activity)
        }
        tv_user_name.text = getText(R.string.to_login)
        ll_user_id.visibility = View.GONE
        ll_user_level_ranking.visibility = View.GONE
    }

    private fun setLoginState() {
        tv_user_name.text = User.username
        tv_user_id.text = User.id.toString()
        ll_user_id.visibility = View.VISIBLE
        ll_user_level_ranking.visibility = View.VISIBLE
        tv_user_level.text = User.icon
        tv_user_ranking.text = User.username
    }

}