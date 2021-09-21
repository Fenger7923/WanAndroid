package com.example.library_widget.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.library_widget.R

/**
 * @author fengerzhang
 * @date 2/5/21 4:44 PM
 */
class BannerView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var mDots: MutableList<View> = arrayListOf()
    private var previousPosition = 0
    private val pressDrawable: Drawable?
    private val normalDrawable: Drawable?
    private val picText: TextView
    private val container: LinearLayout
    private var titleList: MutableList<String> = mutableListOf()

    val viewpager: ViewPager

    init {
        val view = View.inflate(context, R.layout.banner, this)
        picText = view.findViewById(R.id.picture_text)
        viewpager = view.findViewById(R.id.banner_viewpager)
        container = view.findViewById(R.id.point_container)
        pressDrawable = ContextCompat.getDrawable(context, R.drawable.point_pressed)
        normalDrawable = ContextCompat.getDrawable(context, R.drawable.point_normal)
    }

    fun addTitle(title: String) {
        titleList.add(title)
    }

    fun initView(size: Int) {
        for (i in 0 until size) {
            val dotId = addDot(container,normalDrawable)
            mDots.add(dotId)
        }
        picText.text = titleList[0]
        mDots[0].background = pressDrawable

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) {
                val newPosition = position % size
                picText.text = titleList[newPosition]
                mDots[newPosition].background = pressDrawable
                mDots[previousPosition].background = normalDrawable
                previousPosition = newPosition
            }

            override fun onPageScrollStateChanged(state: Int) = Unit
        })
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