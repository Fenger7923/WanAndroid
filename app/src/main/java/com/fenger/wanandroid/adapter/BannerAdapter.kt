package com.fenger.wanandroid.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.view.BannerView


/**
 * @author fengerzhang
 * @date 2/5/21 4:49 PM
 */
class BannerAdapter(private val datas: List<BannerData.Data>, private val viewPager: ViewPager) : PagerAdapter(), BannerView.ScrollingListener {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun getCount(): Int = datas.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image = ImageView(container.context)
        image.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(container.context).load(datas[position % datas.size].imagePath).into(image)
        viewPager.addView(image)
        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // TODO 分页加载删除
    }

    override fun setScroll(scrollTime: Int) = Unit
}