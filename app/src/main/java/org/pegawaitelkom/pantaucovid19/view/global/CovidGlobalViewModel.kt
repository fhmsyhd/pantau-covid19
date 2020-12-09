package org.pegawaitelkom.pantaucovid19.view.global

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidGlobal
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidIndonesia
import org.pegawaitelkom.pantaucovid19.model.ResponseGlobalData
import org.pegawaitelkom.pantaucovid19.network.ApiServiceCorona
import org.pegawaitelkom.pantaucovid19.network.ApiServiceCoronaGlobal
import java.lang.Exception

class CovidGlobalViewModel : ViewModel() {
    private val globalData = MutableLiveData<List<ResponseCovidGlobal>>()

    private val statusCorona = MutableLiveData<ApiServiceCoronaGlobal.ApiStatusCorona>()

    //data status
    fun getStatus(): LiveData<ApiServiceCoronaGlobal.ApiStatusCorona> = statusCorona
    fun getDataIndonesia(): LiveData<List<ResponseCovidGlobal>> = globalData


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestDataIndonesia()
            }
        }
    }

    private suspend fun requestDataIndonesia() {
        try {
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.LOADING)
            val result = ApiServiceCoronaGlobal.serviceDataGlobal.getDataGlobal()
            globalData.postValue(result)
            Log.d("REQUEST_GLOBAL", "Covid : $result")
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.SUCCESS)
        } catch (e: Exception) {
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.FAILED)
            Log.d("REQUEST_GLOBAL", e.message.toString())
        }
    }

}