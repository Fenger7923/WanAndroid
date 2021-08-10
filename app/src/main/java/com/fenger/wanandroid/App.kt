package com.fenger.wanandroid

import android.app.Application
import com.example.library_base.utils.MMKVHelper

/**
 * @author fengerzhang
 * @date 2021/8/10 12:19
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        MMKVHelper.initMMKV(this)

        // TODO 接入log库
    }
}