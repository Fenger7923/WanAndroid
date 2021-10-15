package com.fenger.wanandroid.ui.web

import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.ui.viewinterop.AndroidView
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.constants.Constant


/**
 * @author fengerzhang
 * @date 3/5/21 3:07 PM
 */
class WebViewActivity : BaseActivity() {

    companion object {
        const val JUMP_URL = "jump_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var url = intent.getStringExtra(JUMP_URL)
        if (url.isNullOrEmpty()) {
            Toast.makeText(this, "Page is not found", Toast.LENGTH_SHORT).show()
            url = Constant.REQUEST_BASE_URL
        }
        setContent {
            AndroidView(factory = ::WebView) { webView ->
                with(webView){
                    loadUrl(url)
                }
            }
        }
    }
}