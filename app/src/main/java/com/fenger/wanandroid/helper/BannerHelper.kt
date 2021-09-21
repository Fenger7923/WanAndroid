package com.fenger.wanandroid.helper

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import coil.load
import com.example.library_base.bean.BannerData
import com.example.library_widget.widget.BannerView

/**
 * @author fengerzhang
 * @date 2021/8/11 17:15
 */
class BannerHelper(private val bannerView: BannerView) {
    fun setDatas(bannerDatas: List<BannerData>) {
        for (item in bannerDatas) {
            bannerView.addTitle(item.title)
            bannerView.background
        }
        bannerView.initView(bannerDatas.size)

        bannerView.viewpager.adapter = object : PagerAdapter() {
            override fun getCount(): Int = bannerDatas.size

            override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val image = ImageView(container.context)
                image.scaleType = ImageView.ScaleType.FIT_XY
                image.load(bannerDatas[position % bannerDatas.size].imagePath)
                container.addView(image)
                return image
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                // TODO 分页加载删除
            }
        }
    }

    fun showViewPager() {
        bannerView.visibility = View.VISIBLE
    }
}