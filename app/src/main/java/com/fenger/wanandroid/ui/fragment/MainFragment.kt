package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fenger.wanandroid.R
import com.fenger.wanandroid.adapter.RecyclerViewAdapter
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.bean.MainTabData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO 分页加载
    }

    private fun initView() {
        RetrofitHelper.retrofitService.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initBanner(it)
            }, {
                it.toString()
            })

        RetrofitHelper.retrofitService.getMainTabList(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
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

    private fun initArticleList(result: MainTabData) {
        if (result.errorCode != 0) {
            return
        }

        val mainData = result.data.datas
        if (!mainData.isNullOrEmpty()) {
            article_list.layoutManager = LinearLayoutManager(context)
            article_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            article_list.adapter = RecyclerViewAdapter(mainData)
        }
    }
}