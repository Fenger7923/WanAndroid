package com.fenger.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author fengerzhang
 * @date 2/1/21 9:09 PM
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun setLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
    }
}