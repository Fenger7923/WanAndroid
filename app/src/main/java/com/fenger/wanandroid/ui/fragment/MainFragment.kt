package com.fenger.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fenger.wanandroid.R
import com.fenger.wanandroid.base.BaseFragment
import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.network.RetrofitHelper
import kotlinx.android.synthetic.main.fragment_main.banner_view
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author fengerzhang
 * @date 2/1/21 9:24 PM
 */
class MainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        init()
        return view
    }

    private fun init() {
        initView()
    }

    private fun initView() {
        RetrofitHelper.retrofitService.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initData(it)
            }, {
                it.toString()
            })
    }

    private fun initData(result: BannerData) {
        if (result.errorCode != 0) {
            return
        }
        val bannerData: List<BannerData.Data>? = result.data
        if (!bannerData.isNullOrEmpty()) {
            banner_view.setBannerData(bannerData)
        }
    }
}