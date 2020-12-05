package org.pegawaitelkom.pantaucovid19.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidIndonesia
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidProvinsi
import org.pegawaitelkom.pantaucovid19.network.ApiServiceCorona
import java.lang.Exception

class HomeViewModel: ViewModel() {
    private val indonesiaData = MutableLiveData<ResponseCovidIndonesia>()
    private val provinsiData = MutableLiveData<List<ResponseCovidProvinsi>>()
    private val statusCorona = MutableLiveData<ApiServiceCorona.ApiStatusCorona>()

    //data status
    fun getStatus(): LiveData<ApiServiceCorona.ApiStatusCorona> = statusCorona

    fun getDataIndonesia(): LiveData<ResponseCovidIndonesia> = indonesiaData
    fun getDataProvinsi(): LiveData<List<ResponseCovidProvinsi>> = provinsiData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestDataIndonesia()
            }
        }
    }

    private suspend fun requestDataIndonesia() {
        try {
            statusCorona.postValue(ApiServiceCorona.ApiStatusCorona.LOADING)
            val result = ApiServiceCorona.serviceDataIndonesia.getDataIndonesia()
            val resultProvinsi = ApiServiceCorona.serviceDataIndonesia.getDataProvinsi()
            indonesiaData.postValue(result)
            provinsiData.postValue(resultProvinsi)
            Log.d("REQUEST", "Nama : ${result} && $resultProvinsi")
            statusCorona.postValue(ApiServiceCorona.ApiStatusCorona.SUCCESS)
        } catch (e: Exception) {
            statusCorona.postValue(ApiServiceCorona.ApiStatusCorona.FAILED)
            Log.d("REQUEST", e.message.toString())
        }
    }
}