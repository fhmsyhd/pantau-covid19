package org.pegawaitelkom.pantaucovid19.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.pegawaitelkom.pantaucovid19.constant.Constan.BASE_URL_API
import org.pegawaitelkom.pantaucovid19.constant.Constan.GET_INDONESIA_API
import org.pegawaitelkom.pantaucovid19.constant.Constan.GET_PROVINSI_API
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidIndonesia
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidProvinsi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object ApiServiceCorona {
    enum class ApiStatusCorona { LOADING, SUCCESS, FAILED }

    private val okHttpBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_API)
        .client(okHttpBuilder)
        .build()

    val serviceDataIndonesia: IndonesiaInterface by lazy {
        retrofit.create(IndonesiaInterface::class.java)
    }

    interface IndonesiaInterface {
        @GET(GET_INDONESIA_API)
        suspend fun getDataIndonesia(): ResponseCovidIndonesia

        @GET(GET_PROVINSI_API)
        suspend fun getDataProvinsi(): List<ResponseCovidProvinsi>
    }
}