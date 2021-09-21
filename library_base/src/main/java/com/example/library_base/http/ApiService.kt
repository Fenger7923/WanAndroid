package com.example.library_base.http

import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * @author fengerzhang
 * @date 2/1/21 4:57 PM
 * Retrofit请求api
 */
interface ApiService {
    @GET
    suspend fun get(
        @Url url: String = "",
        @HeaderMap header: Map<String, String>
    ): ResponseBody

    @POST
    suspend fun post(
        @Url url: String = "",
        @HeaderMap header: Map<String, String>,
        @FieldMap params: Map<String, String>
    ): ResponseBody
}