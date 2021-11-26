package com.fenger.wanandroid.ext

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * @author fengerzhang
 * @date 2021/10/13 16:09
 */
inline fun <reified T : Activity> Context.startActivityExt(intent: Intent? = null) {
    val localIntent = intent ?: Intent()
    if (this !is Activity) {
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    localIntent.setClass(this, T::class.java)
    startActivity(localIntent)
}

inline fun <reified T : Activity> Activity.startActivityForResultExt(requestCode: Int, intent: Intent? = null) {
    val localIntent = intent ?: Intent()
    localIntent.setClass(this, T::class.java)
    startActivityForResult(localIntent, requestCode)
}