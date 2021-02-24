package com.fenger.wanandroid.bean

/**
 * @author fengerzhang
 * @date 2/1/21 9:00 PM
 */
data class BannerData(
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<Data>?
) {
    data class Data(
        var id: Int,
        var url: String,
        var imagePath: String,
        var title: String,
        var desc: String,
        var isVisible: Int,
        var order: Int,
        var type: Int
    )
}