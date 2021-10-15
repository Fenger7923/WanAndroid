package com.fenger.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.register.RegisterPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.composethemeadapter.MdcTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                SetLoginInfo()
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun SetLoginInfo() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.blue)),
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
                modifier = Modifier
                    .alpha(0.8f)
                    .padding(top = 5.dp)
            )

            val selectedIndex by remember { mutableStateOf(0) }
            val pagerState = rememberPagerState(pageCount = 2, initialPage = selectedIndex)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp)
                    )
            ) { page ->
                when (page) {
                    0 -> LoginPage(this@LoginActivity, toNextPage = {
                        CoroutineScope(Dispatchers.Default).launch {
                            // TODO 动画效果不太好
                            pagerState.scrollToPage(1)
                        }
                    })
                    1 -> RegisterPage(toNextPage = {
                        CoroutineScope(Dispatchers.Default).launch {
                            pagerState.scrollToPage(0)
                        }
                    })
                }
            }
        }
    }

    @Preview(showBackground = false)
    @Composable
    fun TestLogin() {
        SetLoginInfo()
    }
}