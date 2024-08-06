package com.technical.test.satoritech.api.utils

import okhttp3.Interceptor
import okhttp3.Response

object ApiServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println(request.url)
        println(request.headers)
        println(request.body)
        val requestBuilder = request.newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}