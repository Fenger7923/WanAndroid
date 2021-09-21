package com.example.library_widget.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import kotlin.math.max

/**
 * @author fengerzhang
 * @date 3/10/21 4:37 PM
 * 这段代码自定义组件，过几天绝对看不懂，事实上现在已经看不懂了。。。。
 * 之后去拷贝或者重写一份逻辑正常点的
 */
class FlowLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    // 所有的行
    private val allLinesView = arrayListOf<MutableList<View>>()
    // 每一行的高度位置
    private var lineHeights = arrayListOf<Int>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        allLinesView.clear()
        lineHeights.clear()

        // 整体宽高
        var height = paddingTop
        var width = 0
        // 每一行的宽高
        var lineWidth = 0
        var lineHeight = 0

        // 每一行的 view
        var mLineViews: MutableList<View> = arrayListOf()
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        // 获取当前布局当中子view的格式
        for (child in children) {
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val childHeight = child.measuredHeight
            val childWidth = child.measuredWidth + child.paddingLeft + paddingRight
            if (lineWidth + childWidth > sizeWidth - paddingLeft - paddingRight) {
                width = max(width, lineWidth)
                lineWidth = childWidth + child.paddingRight
                lineHeights.add(height)
                height += lineHeight + paddingTop
                lineHeight = childHeight

                allLinesView.add(mLineViews)
                mLineViews = arrayListOf()
                mLineViews.add(child)
            } else {
                lineWidth += childWidth
                lineHeight = max(lineHeight, childHeight)
                mLineViews.add(child)
            }
        }
        allLinesView.add(mLineViews)

        width = max(width, lineWidth)
        lineHeights.add(height)
        height += lineHeight
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        setMeasuredDimension(
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width + paddingLeft + paddingRight,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height + paddingTop + paddingBottom
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in allLinesView.indices) {
            val lineViews = allLinesView[i]
            var curL = paddingLeft
            lineViews.forEach { view ->
                //每个view 的上下左右点
                val left = curL
                val top = lineHeights[i]
                val right = left + view.measuredWidth
                val bottom = top + view.measuredHeight

                //获取view的上下左右去布局
                view.layout(left,top,right,bottom)
                //下一个view的左起点 上一个view的右起点+行间距
                curL = right + paddingLeft
            }
            curL = paddingLeft
        }
    }
}