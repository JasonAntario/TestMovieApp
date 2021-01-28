package com.example.testmovieapp.data.network

import com.example.testmovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RequestHelper {


    private val baseUrl = "https://api.themoviedb.org/3/"

    private val requestInterceptor = Interceptor { chain ->

        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITmdbApi::class.java)

    fun getClient(): ITmdbApi {
        return retrofit
    }
}