package com.fenger.wanandroid.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author fengerzhang
 * @date 2/5/21 5:04 PM
 */
class Utils {
    companion object {
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
        fun setUrl2Drawable(url: String) {

        }
    }
}