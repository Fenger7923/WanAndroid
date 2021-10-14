package com.fenger.wanandroid.ui.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.library_base.bean.ArticleData
import com.example.library_base.bean.TreeData
import com.example.library_base.http.getArticleList
import com.example.library_base.http.getTypeTreeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * @author fengerzhang
 * @date 2021/10/13 16:19
 *
 * TODO 暂时合并了repo到这里
 */
class TreeViewModel : ViewModel() {

    // 分页加载数据
    private val currentPage: MutableLiveData<Int> = MutableLiveData<Int>(0)

    // 第二层列表id
    private val secondCid: MutableLiveData<Int> = MutableLiveData<Int>(0)

    // 第一页列表id
    private val firstId: MutableLiveData<Int> = MutableLiveData(0)

    // 是否展示第二页
    private val _isInSecondLv: MutableLiveData<Boolean> = MutableLiveData(false)
    val isInSecondLv: LiveData<Boolean> = _isInSecondLv

    private val liveDataMerger = MediatorLiveData<Int>().apply {
        addSource(secondCid) {
            this.value = it
        }
        addSource(currentPage) {
            this.value = it
        }
    }
    // 第二页内容
    val articleListData: LiveData<MutableList<ArticleData>> = liveDataMerger.switchMap {
        flow {
            val result: MutableList<ArticleData> = mutableListOf()
            val articleList =
                getArticleList(currentPage.value ?: 0, secondCid.value ?: 0).data?.datas
                    ?: emptyList()
            articleList.forEach {
                result.add(it)
            }
            emit(result)
        }.flowOn(Dispatchers.Default).asLiveData(viewModelScope.coroutineContext)
    }

    // 第一页内容
    val secondLvData: LiveData<List<TreeData>> = firstId.switchMap { firstId ->
        flow {
            val result = firstLvData.value?.get(firstId ?: 0)?.children
            if (result.isNullOrEmpty()) {
                return@flow
            }
            emit(result)
        }.flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
    }

    // 左侧内容栏
    val firstLvData: LiveData<List<TreeData>> = flow {
        val listData: List<TreeData>? = getTypeTreeList().data
        if (listData.isNullOrEmpty()) {
            getTypeTreeList()
        } else {
            emit(listData)
        }
    }.flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)

    fun showFirstLvView(index: Int) {
        _isInSecondLv.value = true
        firstId.value = index
    }

    fun showSecondLvView(index: Int) {
        _isInSecondLv.value = false
        secondCid.value = index
    }
}