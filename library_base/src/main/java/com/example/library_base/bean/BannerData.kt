package com.example.library_base.bean

import com.example.library_base.http.HttpResponse

/**
 * @author fengerzhang
 * @date 2/1/21 9:00 PM
 */
data class BannerDataList(
    val data: List<BannerData>? = null
) : HttpResponse()

data class BannerData(
    var id: Int,
    var url: String,
    var imagePath: String,
    var title: String,
    var desc: String,
    var isVisible: Int,
    var order: Int,
    var type: Int
)