package com.example.library_base.http

open class HttpResponse @JvmOverloads constructor(
    var errorCode: String = "",
    var errorMsg: String = ""
)