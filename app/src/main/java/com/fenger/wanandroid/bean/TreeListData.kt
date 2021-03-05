package com.fenger.wanandroid.bean

import java.io.Serializable

/**
 * @author fengerzhang
 * @date 3/5/21 4:09 PM
 */
data class TreeListData (
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<Data>
    ) {
        data class Data(
            var id: Int,
            var name: String,
            var courseId: Int,
            var parentChapterId: Int,
            var order: Int,
            var visible: Int,
            var children: List<Children>?
        ) : Serializable {
            data class Children(
                var id: Int,
                var name: String,
                var courseId: Int,
                var parentChapterId: Int,
                var order: Int,
                var visible: Int,
                var children: List<Children>?
            ) : Serializable
        }
    }