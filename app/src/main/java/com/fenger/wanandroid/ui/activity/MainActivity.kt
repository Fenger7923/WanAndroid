package com.fenger.wanandroid.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.FragmentAdapter
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.ui.fragment.MainFragment
import com.fenger.wanandroid.ui.fragment.MyFragment
import com.fenger.wanandroid.ui.fragment.TreeFragment
import com.fenger.wanandroid.view.ColorTrackTextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    companion object {
       private const val TAG = "MainActivity"
    }
    private lateinit var fragments: List<Fragment>
    private lateinit var rb: Array<ColorTrackTextView>
    override fun setLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rb = arrayOf(main_tab, find_tab, news_tab, message_tab, life_tab)

        main_tab.setOnClickListener(this)
        find_tab.setOnClickListener(this)
        news_tab.setOnClickListener(this)
        message_tab.setOnClickListener(this)
        life_tab.setOnClickListener(this)

        initViewPager()
    }

    private fun initViewPager() {
        fragments = listOf(MainFragment(), TreeFragment(), MyFragment(), MyFragment(), MyFragment())
        viewpager_fragment.adapter = FragmentAdapter(supportFragmentManager, fragments)

        // 默认一进来加载的页面
        currentItem(0)

        /**
         * 这里有一个问题需要注意
         * 向左滑动的时候，从一开始position就会跳到滑动后的位置，如从2到1，最开始position就变成了1
         * 向右滑动的时候，知道最后一刻position才变成了滑动后的位置。（注意打印的position的值）
         */
        viewpager_fragment.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                 Log.e(TAG, "position --> $position positionOffset --> $positionOffset")
                if (positionOffset > 0) {
                    // 获取左边
                    val left = rb[position]
                    // 设置朝向
                    left.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
                    // 设置进度  positionOffset 是从 0 一直变化到 1 不信可以看打印
                    left.setProgress(1 - positionOffset)

                    // 获取右边
                    val right = rb[position + 1]
                    right.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT)
                    right.setProgress(positionOffset)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentItem(viewpager_fragment.currentItem)
            }

            override fun onPageSelected(position: Int) = Unit

        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.main_tab -> viewpager_fragment.currentItem = 0
            R.id.find_tab -> viewpager_fragment.currentItem = 1
            R.id.news_tab -> viewpager_fragment.currentItem = 2
            R.id.message_tab -> viewpager_fragment.currentItem = 3
            R.id.life_tab -> viewpager_fragment.currentItem = 4
        }
    }

    /**
     * 点击某个栏目算作刚进入
     */
    private fun currentItem(item: Int) {
        viewpager_fragment.currentItem = item
        //先初始化其他项目
        for (i in rb) {
            i.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
            i.setProgress(0F)
        }

        // 选中的项目变成红色
        rb[item].setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT)
        rb[item].setProgress(1f)
    }
}