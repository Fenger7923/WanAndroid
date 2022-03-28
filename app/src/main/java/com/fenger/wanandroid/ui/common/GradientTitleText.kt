package com.fenger.wanandroid.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.fenger.wanandroid.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

/**
 * @author fengerzhang
 * @date 2021/11/30 11:30
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun GradientTitleText(
    modifier: Modifier,
    index: Int,
    pagerState: PagerState,
    clickInvoke: (() -> Unit)? = null
) {
    val titleList = listOf(
        stringResource(id = R.string.main_tab),
        stringResource(id = R.string.tree_tab),
        stringResource(id = R.string.news_tab),
        stringResource(id = R.string.message_tab),
        stringResource(id = R.string.life_tab)
    )
    Log.e(
        "fenger",
        "current page is ${pagerState.currentPage}, offset is ${pagerState.currentPageOffset},target is ${pagerState.targetPage}"
    )

    val offsetPercent: Float
    var direction = Direction.LTR
    val current = pagerState.currentPage
    val offset = pagerState.currentPageOffset
    val target = pagerState.targetPage
    /**
     * 1. cur = index, target = index ->
     */
    if (current == index && pagerState.targetPage == index) {
        offsetPercent = -pagerState.currentPageOffset
        direction = Direction.LTR
    } else if ((pagerState.targetPage == index + 1 && pagerState.currentPage == index) && pagerState.currentPageOffset < 0) {
        offsetPercent = pagerState.currentPageOffset
    } else if (pagerState.currentPage != index && pagerState.targetPage == index && pagerState.currentPageOffset < 0) {
        offsetPercent = - pagerState.currentPageOffset
        direction = Direction.RTL
    } else if (current == index && offset == 0f) {
        offsetPercent = 0f
        direction = Direction.LTR
    } else if (offset == 0f) {
        offsetPercent = 1f
        direction = Direction.LTR
    } else {
        offsetPercent = 1f
        direction = Direction.LTR
    }

    Box(modifier = modifier) {
        DrawGradientText(
            text = titleList[index],
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    clickInvoke?.invoke()
                },
            fromColor = Color.Black,
            endColor = Color.Red,
            percent = offsetPercent,
            direction = direction,
            fontSize = 20.sp
        )
    }
}