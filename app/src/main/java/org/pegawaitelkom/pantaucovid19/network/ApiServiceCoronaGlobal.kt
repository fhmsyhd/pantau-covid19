package org.pegawaitelkom.pantaucovid19.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.pegawaitelkom.pantaucovid19.constant.Constan
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidGlobal
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidIndonesia
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidProvinsi
import org.pegawaitelkom.pantaucovid19.model.ResponseGlobalData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object ApiServiceCoronaGlobal {
    enum class ApiStatusCorona { LOADING, SUCCESS, FAILED }

    private val okHttpBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constan.BASE_URL_KAWAL)
        .client(okHttpBuilder)
        .build()

    val serviceDataGlobal: GlobalInterface by lazy {
        retrofit.create(GlobalInterface::class.java)
    }

    interface GlobalInterface {
        @GET("/")
        suspend fun getDataGlobal(): List<ResponseCovidGlobal>
        @GET(Constan.GET_POSITIF)
        suspend fun getPositifGlobal():ResponseGlobalData
        @GET(Constan.GET_SEMBUH)
        suspend fun getSembuhGlobal():ResponseGlobalData
        @GET(Constan.GET_MENINGGAL)
        suspend fun getMeninggalGlobal():ResponseGlobalData
    }
}