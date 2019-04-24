package it.chutien.forextime.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import it.chutien.forextime.BuildConfig
import it.chutien.forextime.data.remote.ApiService
import it.chutien.forextime.data.remote.RxErrorHandlingCallAdapterFactory
import it.chutien.forextime.di.Properties.TIME_OUT
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ChuTien on ${1/25/2017}.
 */
val networkModule = module(override = true){
    single { createOkHttpCache(get()) }
    single { createLoggingInterceptor() }
    single { createHeaderInterceptor() }
    single { createOkHttpClient(get(), get(), get()) }
    single { createAppRetrofit(get()) }
    single { createApiService(get()) }
}

object Properties {
    const val TIME_OUT = 60
}

fun createOkHttpCache(context : Context): Cache {
    val size = (10 * 1024 * 1024).toLong() // 10 Mb
    return Cache(context.cacheDir, size)
}

fun createLoggingInterceptor(): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return logging
}

fun createHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .header("Content-Type", "application/json")
            .method(request.method(), request.body())
            .build()
        chain.proceed(newRequest)
    }
}

fun createOkHttpClient(
    cache: Cache,
    logging: Interceptor,
    header: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(StethoInterceptor())
        .addInterceptor(header)
        .addInterceptor(logging)
        .build()
}

fun createAppRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}

fun createApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}