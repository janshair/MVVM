package com.tapdevs.core.injections

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tapdevs.core.network.DispatcherType
import com.tapdevs.core.network.ResponseHandler
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import com.tapdevs.core.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory

    val networkModule  = module {
        factory { HttpLoggingInterceptor() }
        factory { provideOkHttpClient(get()) }
        factory { ResponseHandler() }
        single { provideRetrofit(get(), BuildConfig.BASE_URL) }

        factory { CoroutineCallAdapterFactory() }
        factory(qualifier = named(DispatcherType.IO)) { Dispatchers.IO }
        factory(qualifier = named(DispatcherType.MAIN)) { Dispatchers.Main }
    }

fun getLoggingInterceptor(httpLoggingInterceptor: HttpLoggingInterceptor): HttpLoggingInterceptor {
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    return httpLoggingInterceptor
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

    return OkHttpClient().newBuilder().addInterceptor(getLoggingInterceptor(httpLoggingInterceptor)).build()
}


fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder().baseUrl(url).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}