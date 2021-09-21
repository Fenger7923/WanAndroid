package com.example.library_base.bean

import com.example.library_base.http.HttpResponse

/**
 * @author fengerzhang
 * @date 3/18/21 8:51 PM
 */
data class LoginData(
    val data: UserInfoData? = null
) : HttpResponse()

data class RegisterData(
    val data: UserInfoData? = null
) : HttpResponse()

data class UserInfoData constructor(
    var admin: Boolean,
    var chapterTops: List<Int>,
    var coinCount: Int,
    var collectIds: List<Int>,
    var email: String,
    var icon: String,
    var id: Int,
    var nickname: String,
    var password: String,
    var publicName: String,
    var token: String,
    var type: Int,
    var username: String
)