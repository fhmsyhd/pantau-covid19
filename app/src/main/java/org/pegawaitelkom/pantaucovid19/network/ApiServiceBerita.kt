package org.pegawaitelkom.pantaucovid19.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.pegawaitelkom.pantaucovid19.model.ResponseNews
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object ApiServiceBerita {
    enum class ApiStatus { LOADING, SUCCESS, FAILED }

    private val okHttpBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://newsapi.org")
        .client(okHttpBuilder)
        .build()

    val serviceBerita: BeritaInterface by lazy {
        retrofit.create(BeritaInterface::class.java)
    }

    interface BeritaInterface {
        @GET("/v2/top-headlines")
        suspend fun getListNews(
            @Query("country") country: String?,
            @Query("category") category: String?,
            @Query("apiKey") apiKey: String?
        ): ResponseNews
    }
}

