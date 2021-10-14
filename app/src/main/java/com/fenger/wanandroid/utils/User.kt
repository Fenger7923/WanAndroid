package com.fenger.wanandroid.utils

import android.content.Context
import com.example.library_base.bean.LoginData
import com.fenger.wanandroid.ui.LoginActivity

/**
 * @author fengerzhang
 * @date 3/31/21 5:43 PM
 */
object User {
    var isLogin = false
    var admin: Boolean = false
    var chapterTops: List<Int> = mutableListOf()
    var coinCount: Int = 0
    var collectIds: List<Int> = mutableListOf()
    var email: String = ""
    var icon: String = ""
    var id: Int = 0
    var nickname: String = ""
    var password: String = ""
    var publicName: String = ""
    var token: String = ""
    var type: Int = 0
    var username: String = ""

    fun doIfLogin(context: Context?): Boolean {
        return if (isLogin) {
            true
        } else {
            LoginActivity.start(context)
            false
        }
    }

    fun setUser(result: LoginData) {
        val userData = result.data ?: return
        isLogin = true
        admin = userData.admin
        id = userData.id
        username = userData.username
        password = userData.password
        nickname = userData.nickname
        email = userData.email
        collectIds = userData.collectIds
    }
}