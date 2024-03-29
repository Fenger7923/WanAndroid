package com.fenger.wanandroid.screen.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import com.example.library_base.bean.BannerData
import com.fenger.wanandroid.adapter.ArticleDataItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

/**
 * @author fengerzhang
 * @date 2021/10/14 17:36
 */
@Composable
fun MainPage(mainViewModel: MainViewModel = MainViewModel()) {
    val bannerDataList by mainViewModel.bannerDataList.observeAsState(listOf())
    val refreshState = rememberSwipeRefreshState(isRefreshing = false)
    val articleListData = mainViewModel.articleListData1.collectAsLazyPagingItems()

    Column {
        Banner(bannerDataList)

        SwipeRefresh(
            state = refreshState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onRefresh = { mainViewModel.refreshList() }) {
            LazyColumn {
                itemsIndexed(articleListData) { _, item ->
                    if (item != null) {
                        ArticleDataItem(item)
                        Divider(color = Color.LightGray)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(bannerDataList: List<BannerData>) {
    // TODO bug也挺多的  滑动几次之后切换到其他的页面再回来就crash

    val timeMillis = 3000L
    val pagerState = rememberPagerState(
        pageCount = bannerDataList.size,
        initialOffscreenLimit = 1,
        infiniteLoop = true
    )

    Box(modifier = Modifier.height(200.dp)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            Image(
                painter = rememberImagePainter(bannerDataList[page].imagePath),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            //自动滚动
            LaunchedEffect(pagerState.currentPage) {
                if (pagerState.pageCount > 0) {
                    delay(timeMillis)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(6.dp),
            activeColor = Color.White,
            inactiveColor = Color.Black
        )
    }
}
