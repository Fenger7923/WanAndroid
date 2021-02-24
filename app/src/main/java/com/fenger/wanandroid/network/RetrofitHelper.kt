package com.fenger.wanandroid.network

import com.fenger.wanandroid.constants.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author fengerzhang
 * @date 2/1/21 4:26 PM
 */
object RetrofitHelper {
    private const val TAG = "RetrofitHelper"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    /**
     * 只需要一个OkHttpClient对象
     */
    private var mOkHttpClient: OkHttpClient? = null

    val retrofitService: RetrofitService = getService(Constant.REQUEST_BASE_URL, RetrofitService::class.java)

    private fun create(baseUrl: String): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            cache()  缓存之后再做吧
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        }.build()

        // TODO 确认retrofit的几个方法
        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        }.build()
    }

    /**
     * get ServiceApi
     */
    private fun <T> getService(url: String, service: Class<T>): T = create(url).create(service)
}