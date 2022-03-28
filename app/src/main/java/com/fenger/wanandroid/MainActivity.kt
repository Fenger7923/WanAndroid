package com.fenger.wanandroid

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.UserPage
import com.fenger.wanandroid.screen.mainScreen.MainPage
import com.fenger.wanandroid.screen.treeScreen.TreePage
import com.fenger.wanandroid.ui.common.Direction
import com.fenger.wanandroid.ui.common.DrawGradientText
import com.fenger.wanandroid.ui.common.GradientTitleText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pagerState: PagerState = rememberPagerState(pageCount = 5)

            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(color = colorResource(id = R.color.blue))
                    ) {
                        GradientTitleText(
                            modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
                            index = 0,
                            pagerState = pagerState,
                        ) {
                            jumpToPage(0, pagerState)
                        }
                        GradientTitleText(
                            modifier = Modifier.weight(1f).align(Alignment.CenterVertically),
                            index = 1,
                            pagerState = pagerState,
                        ) {
                            jumpToPage(1, pagerState)
                        }
                        DrawGradientText(
                            text = stringResource(id = R.string.news_tab),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    jumpToPage(2, pagerState)
                                },
                            fromColor = Color.Red,
                            endColor = Color.Blue,
                            percent = pagerState.currentPageOffset,
                            direction = Direction.LTR,
                            fontSize = 20.sp
                        )
                        DrawGradientText(
                            text = stringResource(id = R.string.message_tab),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    jumpToPage(3, pagerState)
                                },
                            fromColor = Color.Red,
                            endColor = Color.Blue,
                            percent = pagerState.currentPageOffset,
                            direction = Direction.LTR,
                            fontSize = 20.sp
                        )
                        DrawGradientText(
                            text = stringResource(id = R.string.life_tab),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    jumpToPage(4, pagerState)
                                },
                            fromColor = Color.Red,
                            endColor = Color.Blue,
                            percent = pagerState.currentPageOffset,
                            direction = Direction.LTR,
                            fontSize = 20.sp
                        )
                    }
                }
            ) {

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.white),
                            shape = RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp)
                        )
                ) { page ->
                    when (page) {
                        0 -> MainPage()
                        1 -> TreePage()
                        2, 3, 4 -> UserPage()
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalPagerApi::class)
    private fun jumpToPage(page: Int, pagerState: PagerState) {
        lifecycleScope.launch {
            pagerState.scrollToPage(page = page)
        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}