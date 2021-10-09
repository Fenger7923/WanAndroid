package com.fenger.wanandroid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.FragmentAdapter
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.fragment.LoginFragment
import com.fenger.wanandroid.ui.fragment.RegisterFragment
import com.google.android.material.composethemeadapter.MdcTheme

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
        val composeView = findViewById<ComposeView>(R.id.login_compose_view)
        composeView.setContent {
            MdcTheme {
                SetLoginInfo()
            }
        }
    }

    @Composable
    fun SetLoginInfo() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.pager_image1),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 80.dp, bottom = 20.dp)
                    .size(100.dp, 100.dp)
            )
            Text(
                text = stringResource(id = R.string.welcome),
                fontSize = 12.sp,
                color = colorResource(id = R.color.white),
            )
            Text(
                text = stringResource(id = R.string.app_fenger),
                fontSize = 12.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier.alpha(0.8f).padding(top = 5.dp)
            )

        }
    }

    @Preview(showBackground = false)
    @Composable
    fun TestLogin() {
        SetLoginInfo()
    }

    private fun initView() {
//        val fragments = listOf(LoginFragment(), RegisterFragment())
//        login_viewpager.adapter = FragmentAdapter(supportFragmentManager, fragments)
//        changeToLogin()
    }

    fun changeToRegister() {
//        login_viewpager.currentItem = 1
    }

    fun changeToLogin() {
//        login_viewpager.currentItem = 0
    }
}