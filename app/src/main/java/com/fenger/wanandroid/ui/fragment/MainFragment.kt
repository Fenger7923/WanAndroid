package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.library_base.bean.ArticleData
import com.example.library_base.bean.ArticleDataList
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.RecyclerViewAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.example.library_base.bean.BannerData
import com.example.library_base.http.getBanner
import com.example.library_base.http.getMainTabList
import com.example.library_widget.widget.BannerView
import com.fenger.wanandroid.helper.BannerHelper
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainFragment : BaseFragment() {

    private var isLoading = false
    private var articleListData: MutableList<ArticleData> = mutableListOf()
    private var curPage = 0
    private var totalPage = 0

    private lateinit var articleListView: RecyclerView
    private lateinit var swipeView: SwipeRefreshLayout
    private lateinit var bannerView: BannerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, null, false)
        articleListView = view.findViewById(R.id.article_list)
        bannerView = view.findViewById(R.id.banner_view)
        swipeView = view.findViewById(R.id.swipe_rl)
        return view
    }

    override fun initView() {
        MainScope().launch {
            getBanner().data?.let { initBanner(it) }
        }

        articleListView.layoutManager = LinearLayoutManager(context)
        articleListView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        articleListView.adapter = RecyclerViewAdapter(articleListData)

        articleListView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val total = (articleListView.layoutManager as LinearLayoutManager).itemCount
                val last = (articleListView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (last == total -1 && !swipeView.isRefreshing && !isLoading) {
                    isLoading = true
                    if (curPage <= totalPage) {
                        curPage ++
                        loadArticleData(curPage)
                    }
                }
            }
        })
            loadArticleData(0)

        swipeView.setOnRefreshListener {
            articleListData.clear()
            articleListView.removeAllViews()
            loadArticleData(0)
        }
    }

    private fun loadArticleData(page: Int) {
        MainScope().launch {
            val list = getMainTabList(page)
            isLoading = false
            swipeView.isRefreshing = false
            initArticleList(list)
        }
    }

    private fun initBanner(datas: List<BannerData>) {
        val bannerHelper = BannerHelper(bannerView)
        bannerHelper.setDatas(datas)
        bannerHelper.showViewPager()
    }

    private fun initArticleList(articleDataList: ArticleDataList) {
        val articleList = articleDataList.data ?: return

        if (curPage == 0) {
            totalPage = articleList.pageCount - 1
        }

        articleList.datas?.forEach {
            articleListData.add(it)
        }

        articleListView.adapter?.notifyDataSetChanged()
    }
}