package com.fenger.wanandroid.network

import com.fenger.wanandroid.bean.BannerData
import com.fenger.wanandroid.bean.MainTabData
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
     */
    @GET("/article/list/{page}/json")
    fun getMainTabList(@Path("page") page: Int): Observable<MainTabData>

    /**
     * 首页Banner
     * @return BannerResponse
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BannerData>
}