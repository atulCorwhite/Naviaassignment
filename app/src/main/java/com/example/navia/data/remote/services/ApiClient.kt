package com.example.navia.data.remote.services

import com.example.navia.common.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    fun getInstance(): Retrofit {
        var mRetrofit: Retrofit? = null

        if (mRetrofit == null) {
            val logginInterceptor = HttpLoggingInterceptor()
            logginInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.readTimeout(60, TimeUnit.SECONDS)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            val builder = Retrofit.Builder().baseUrl(Const.BASE_URL)
            httpClient.addInterceptor(logginInterceptor)
            builder.client(httpClient.build())
            mRetrofit = builder
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return mRetrofit!!
    }
}
