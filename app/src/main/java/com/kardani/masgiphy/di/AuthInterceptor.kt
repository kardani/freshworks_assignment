package com.kardani.masgiphy.di

import com.kardani.masgiphy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val originalHttpUrl = req.url

        val newUrl = originalHttpUrl
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        req = req.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(req)
    }

}