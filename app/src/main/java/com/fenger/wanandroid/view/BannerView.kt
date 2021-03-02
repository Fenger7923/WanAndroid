package com.fenger.wanandroid.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.BannerAdapter
import com.fenger.wanandroid.bean.BannerData
import kotlinx.android.synthetic.main.banner.view.banner_viewpager
import kotlinx.android.synthetic.main.banner.view.picture_text
import kotlinx.android.synthetic.main.banner.view.point_container

/**
 * @author fengerzhang
 * @date 2/5/21 4:44 PM
 */
class BannerView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var mDots: MutableList<View> = arrayListOf()
    private var previousPosition = 0
    private val pressDrawable = ContextCompat.getDrawable(context, R.drawable.point_pressed)
    private val normalDrawable = ContextCompat.getDrawable(context, R.drawable.point_normal)

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        View.inflate(context, R.layout.banner, this)
    }

    fun setBannerData(datas: List<BannerData.Data>) {
        initDots(datas)
        picture_text.text = datas[0].title
        mDots[0].background = pressDrawable
        banner_viewpager.adapter = BannerAdapter(datas, banner_viewpager)
        banner_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) {
                val newPosition = position % datas.size
                picture_text.text = datas[newPosition].title
                mDots[newPosition].background = pressDrawable
                mDots[previousPosition].background = normalDrawable
                previousPosition = newPosition
            }

            override fun onPageScrollStateChanged(state: Int) = Unit
        })
    }

    private fun initDots(datas: List<BannerData.Data>) {
        for (i in datas.indices) {
            val dotId = addDot(point_container,normalDrawable)
            mDots.add(dotId)
        }
    }

    private fun addDot(linearLayout: LinearLayout, drawable: Drawable?): View {
        val dotParams = LinearLayout.LayoutParams(20, 20).apply {
            width = 20
            height = 20
            setMargins(20, 5, 0, 0)
        }
        val dot = View(context).apply {
            layoutParams = dotParams
            background = drawable
            id = View.generateViewId()
        }
        linearLayout.addView(dot)
        return dot
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> mListener?.setScroll(0)
            MotionEvent.ACTION_UP -> mListener?.setScroll(2500)
        }
        return super.dispatchTouchEvent(ev)
    }

    private var mListener: ScrollingListener? = null

    fun setScrollingListener(listener: ScrollingListener?) {
        mListener = listener
    }

    interface ScrollingListener {
        fun setScroll(scrollTime: Int)
    }
}