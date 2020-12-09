package org.pegawaitelkom.pantaucovid19.constant

object Constan {
    const val API_KEY = "91d07fb607c448b2bb3bb7cfd3709cc1"
    const val INTENT_DETAIL ="intent_detail"

    // COVID-19
    const val CORONA_DB = "corona_db"
    const val DATA_INDONESIA = "data_indonesia"
    const val DATA_PROVINSI = "data_provinsi"
    const val DATA_GLOBAL = "data_global"

    // Network utils
    const val BASE_URL_KAWAL = "https://api.kawalcorona.com"
    const val GET_INDONESIA = "/indonesia"
    const val GET_PROVINSI = "/indonesia/provinsi"
    const val GET_POSITIF = "/positif"
    const val GET_SEMBUH = "/sembuh"
    const val GET_MENINGGAL = "/meninggal"

    // api lain
    const val BASE_URL_API = "https://apicovid19indonesia-v2.vercel.app/"
    const val GET_INDONESIA_API = "api/indonesia/more"
    const val GET_PROVINSI_API = "api/indonesia/provinsi"

    // UI utils
    const val SEARCH_HINT = "Cari provinsi..."
    const val DATE_PATTERN = "EEEE, dd MMMM yyyy"
}