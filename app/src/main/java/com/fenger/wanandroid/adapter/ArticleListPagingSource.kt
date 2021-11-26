package com.fenger.wanandroid.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.library_base.bean.ArticleData
import com.example.library_base.http.getMainTabList

/**
 * @author fengerzhang
 * @date 2021/10/20 21:01
 */
class ArticleListPagingSource : PagingSource<Int, ArticleData>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleData> {
        val result = getMainTabList(params.key ?: 0)
        return LoadResult.Page(
            data = result.data?.datas ?: emptyList(),
            prevKey = params.key,
            nextKey = (params.key ?: 0) + 1
        )
    }
}