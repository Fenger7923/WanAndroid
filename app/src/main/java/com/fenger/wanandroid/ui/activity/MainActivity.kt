package com.fenger.wanandroid.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.library_widget.widget.ColorTrackTextView
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.UserPage
import com.fenger.wanandroid.ui.main.MainPage
import com.fenger.wanandroid.ui.tree.TreePage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

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

//        initViewPager()
//
//        initUserInfo()

        setContent {
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(color = colorResource(id = R.color.blue))
                    ) {
                        Text("123")

                    }
                }
            ) {
                val pagerState: PagerState = rememberPagerState(pageCount = 5)

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

//    private fun initViewPager() {
//        val fragments = listOf(MainFragment(), TreeFragment(), MyFragment(), MyFragment(), MyFragment())
//        viewpager_fragment.adapter = FragmentAdapter(supportFragmentManager, fragments)
//
//        // 默认一进来加载的页面
//        currentItem(0)
//
//        /**
//         * 这里有一个问题需要注意
//         * 向左滑动的时候，从一开始position就会跳到滑动后的位置，如从2到1，最开始position就变成了1
//         * 向右滑动的时候，知道最后一刻position才变成了滑动后的位置。（注意打印的position的值）
//         */
//        viewpager_fragment.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                 Log.e(TAG, "position --> $position positionOffset --> $positionOffset")
//                if (positionOffset > 0) {
//                    // 获取左边
//                    val left = rb[position]
//                    // 设置朝向
//                    left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
//                    // 设置进度  positionOffset 是从 0 一直变化到 1 不信可以看打印
//                    left.setProgress(1 - positionOffset)
//
//                    // 获取右边
//                    val right = rb[position + 1]
//                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT)
//                    right.setProgress(positionOffset)
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                currentItem(viewpager_fragment.currentItem)
//            }
//
//            override fun onPageSelected(position: Int) = Unit
//
//        })
//    }

//    private fun initUserInfo() {
//        val username = "fengerzzz"
//        val password = "1234567"
//        MainScope().launch {
//            val result = login(username, password)
//            User.setUser(result)
//        }
//    }


//    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.main_tab -> viewpager_fragment.currentItem = 0
//            R.id.find_tab -> viewpager_fragment.currentItem = 1
//            R.id.news_tab -> viewpager_fragment.currentItem = 2
//            R.id.message_tab -> viewpager_fragment.currentItem = 3
//            R.id.life_tab -> viewpager_fragment.currentItem = 4
//        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    /**
     * 点击某个栏目算作刚进入
     */
//    private fun currentItem(item: Int) {
//        viewpager_fragment.currentItem = item
//        //先初始化其他项目
//        for (i in rb) {
//            i.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
//            i.setProgress(0F)
//        }
//
//        // 选中的项目变成红色
//        rb[item].setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
//        rb[item].setProgress(1f)
//    }
}