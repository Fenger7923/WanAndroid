package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.RecyclerViewAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.ArticleListData
import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_main.article_list
import kotlinx.android.synthetic.main.fragment_main.banner_view
import kotlinx.android.synthetic.main.fragment_main.swipe_rl
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainFragment : BaseFragment() {

    private var isLoading = false
    private var articleListData: MutableList<ArticleListData.Data.Datas> = mutableListOf()
    private var curPage = 0
    private var totalPage = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun initView() {
        RetrofitHelper.retrofitService.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initBanner(it)
            }, {
                it.toString()
            })

        article_list.layoutManager = LinearLayoutManager(context)
        article_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        article_list.adapter = RecyclerViewAdapter(articleListData)

        article_list.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val total = (article_list.layoutManager as LinearLayoutManager).itemCount
                val last = (article_list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (last == total -1 && !swipe_rl.isRefreshing && !isLoading) {
                    isLoading = true
                    if (curPage <= totalPage) {
                        curPage ++
                        loadArticleData(curPage)
                    }
                }
            }
        })
            loadArticleData(0)

        swipe_rl.setOnRefreshListener {
            articleListData.clear()
            article_list.removeAllViews()
            loadArticleData(0)
        }
    }

    private fun loadArticleData(page: Int) {
        RetrofitHelper.retrofitService.getMainTabList(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLoading = false
                swipe_rl.isRefreshing = false
                initArticleList(it)
            }, {
                it.toString()
            })
    }

    private fun initBanner(result: BannerData) {
        if (result.errorCode != 0) {
            return
        }
        val bannerData: List<BannerData.Data>? = result.data
        if (!bannerData.isNullOrEmpty()) {
            banner_view.setBannerData(bannerData)
        }
    }

    private fun initArticleList(result: ArticleListData) {
        if (result.errorCode != 0) {
            return
        }
        if (curPage == 0) {
            totalPage = result.data.pageCount - 1
        }

        result.data.datas?.forEach {
            articleListData.add(it)
        }

        article_list.adapter?.notifyDataSetChanged()
    }
}