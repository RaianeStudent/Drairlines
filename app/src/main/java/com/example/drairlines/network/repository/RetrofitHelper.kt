package com.example.drairlines.network.repository

import android.content.Context
import com.example.drairlines.data.AppConstants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {

    private lateinit var apiService: ApiService

    fun getApiService(context: Context): ApiService {
        // Inicializa o ApiService se ainda não foi inicializado
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder().baseUrl(AppConstants.APP_BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(context)) // Add our Okhttp client
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }
    /**
     * Inicialize OkhttpClient com nosso interceptor
     */
    private fun okhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .build()
    }
}