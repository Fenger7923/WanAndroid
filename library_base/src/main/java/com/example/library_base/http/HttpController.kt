package com.example.library_base.http

import com.example.library_base.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @author fengerzhang
 * @date 2021/8/13 16:49
 */
object HttpController {

    private var baseUrl = Constant.REQUEST_BASE_URL

    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    private var retrofit: Retrofit? = null
    private var service: ApiService? = null
    private var converter: Converter = GSonConverter.create()

    interface Converter {
        fun <T> converter(responseBody: ResponseBody, type: Class<T>): T
    }

    private fun buildHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            cache()  // TODO 缓存之后再做吧
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(buildHttpClient())
            .build()
    }

    private fun getRetrofit() = retrofit ?: synchronized(this) {
        retrofit ?: buildRetrofit().also { retrofit = it }
    }

    /**
     * get ServiceApi
     */
    private fun getService() = service ?: synchronized(this) {
        service ?: getRetrofit().create(ApiService::class.java).also { service = it }
    }

    suspend fun <T : HttpResponse> get(
        request: HttpRequest = HttpRequest(),
        type: Class<T>
    ): T {
        return withContext(Dispatchers.IO) {
            try {
                converter.converter(getService().get(request.getUrl(), request.getHeader()), type)
            } catch (e: Exception) {
                val msg = e.message.toString()
                val t = type.newInstance()
                t.errorMsg = msg
                t
            }
        }
    }

    suspend fun <T : HttpResponse> post(
        request: HttpRequest = HttpRequest(),
        type: Class<T>
    ): T {
        return withContext(Dispatchers.IO) {
            try {
                converter.converter(
                    getService().post(
                        request.getUrl(baseUrl),
                        request.getHeader(),
                        request.getParam()
                    ), type
                )
            } catch (e: Exception) {
                val msg = e.message.toString()
                val t = type.newInstance()
                t.errorMsg = msg
                t
            }
        }
    }
}
