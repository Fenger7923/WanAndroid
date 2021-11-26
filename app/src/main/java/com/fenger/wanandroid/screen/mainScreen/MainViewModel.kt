package com.fenger.wanandroid.screen.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.library_base.bean.ArticleData
import com.example.library_base.bean.BannerData
import com.example.library_base.http.getBanner
import com.example.library_base.http.getMainTabList
import com.fenger.wanandroid.adapter.ArticleListPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author fengerzhang
 * @date 2021/10/14 17:46
 */
class MainViewModel : ViewModel() {
    // 分页加载数据
    private val currentPage = MutableLiveData(0)

    private val articleListData: LiveData<MutableList<ArticleData>> = currentPage.switchMap {
        flow {
            val result: MutableList<ArticleData> = mutableListOf()
            val articleList =
                getMainTabList(currentPage.value ?: 0).data?.datas
            articleList?.forEach {
                result.add(it)
            }
            emit(result)
        }.flowOn(Dispatchers.Default).asLiveData(viewModelScope.coroutineContext)
    }

    val articleListData1: Flow<PagingData<ArticleData>> = Pager(PagingConfig(pageSize = 10)) {
        ArticleListPagingSource()
    }.flow.cachedIn(viewModelScope)

    fun refreshList() {
        articleListData.value?.clear()
        currentPage.value = 0
    }

    val bannerDataList: LiveData<List<BannerData>> = flow {
        val result: MutableList<BannerData> = mutableListOf()
        getBanner().data?.let { result.addAll(it) }
        emit(result)
    }.asLiveData(viewModelScope.coroutineContext)
}