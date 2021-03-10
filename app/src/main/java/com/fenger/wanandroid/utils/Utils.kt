package com.fenger.wanandroid.utils

import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author fengerzhang
 * @date 2/5/21 5:04 PM
 */
object Utils {
    @JvmStatic
    fun getImageByUrl(context: Context, url: String, imageView: ImageView) {
        val bitmap = Glide.with(context)
            .load(url)
            .asBitmap()
            .into(500, 500)
            .get()

        imageView.setImageBitmap(bitmap)
    }

    @JvmStatic
    fun sp2px(sp: Int, context: Context): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), context.resources.displayMetrics)
    }
}