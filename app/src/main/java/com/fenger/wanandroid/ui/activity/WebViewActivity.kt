package com.fenger.wanandroid.ui.activity

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseActivity
import com.fenger.wanandroid.constants.Constant
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_webview.web_view


/**
 * @author fengerzhang
 * @date 3/5/21 3:07 PM
 */
class WebViewActivity : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_webview

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
        AgentWeb.with(this)
                .setAgentWebParent(web_view, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url)
    }
}