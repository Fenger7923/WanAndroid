package com.example.library_base.bean

import com.example.library_base.http.HttpResponse

/**
 * @author fengerzhang
 * @date 3/5/21 4:09 PM
 */
data class TreeListData(
    val data: List<TreeData>? = null,
) : HttpResponse()

data class TreeData(
    var id: Int,
    var name: String,
    var courseId: Int,
    var parentChapterId: Int,
    var order: Int,
    var visible: Int,
    var children: List<TreeData>? = null
)
