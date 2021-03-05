package com.fenger.wanandroid.network

import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.bean.MainTabData
import com.fenger.wanandroid.bean.TreeListData
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * @author fengerzhang
 * @date 2/1/21 4:57 PM
 * Retrofit请求api
 */
interface RetrofitService {

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     * @param page page
     * @return MainTabData
     */
    @GET("/article/list/{page}/json")
    fun getMainTabList(@Path("page") page: Int): Observable<MainTabData>

    /**
     * 首页Banner
     * @return BannerData
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BannerData>

    /**
     * 知识体系
     * @return TreeListData
     */
    @GET("/tree/json")
    fun getTypeTreeList(): Observable<TreeListData>
}