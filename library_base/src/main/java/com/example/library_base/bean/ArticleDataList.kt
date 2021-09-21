package com.example.library_base.bean

import com.example.library_base.http.HttpResponse

/**
 * @author fengerzhang
 * @date 2/1/21 5:43 PM
 */
data class ArticleDataList(
    val data: ArticleList?
) : HttpResponse()

data class ArticleList(
    var offset: Int,
    var size: Int,
    var total: Int,
    var pageCount: Int,
    var curPage: Int,
    var over: Boolean,
    var datas: List<ArticleData>?
)

data class ArticleData(
    var id: Int,
    var originId: Int,
    var title: String,
    var chapterId: Int,
    var chapterName: String?,
    var envelopePic: Any,
    var link: String,
    var author: String,
    var origin: Any,
    var publishTime: Long,
    var zan: Any,
    var desc: Any,
    var visible: Int,
    var niceDate: String,
    var courseId: Int,
    var collect: Boolean,
    var shareUser: String
)



