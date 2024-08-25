package com.example.mygithub.module

import com.example.mygithub.api.ApiService
import com.example.mygithub.api.RetrofitHelper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent ::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitHelper.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .setLenient()
                .create()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val newUrl = originalRequest.url.newBuilder()
                    .build()
                val newRequest = originalRequest.newBuilder().url(newUrl).build()
                chain.proceed(newRequest)
            }.build())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService( retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java);
    }
}