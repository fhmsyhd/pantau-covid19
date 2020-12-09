package org.pegawaitelkom.pantaucovid19.view.global

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pegawaitelkom.pantaucovid19.model.ResponseGlobalData
import org.pegawaitelkom.pantaucovid19.network.ApiServiceCoronaGlobal
import java.lang.Exception

class DetailCovidViewModel:ViewModel() {
    private val globalDataPositif = MutableLiveData<ResponseGlobalData>()
    private val globalDataSembuh = MutableLiveData<ResponseGlobalData>()
    private val globalDataMeninggal = MutableLiveData<ResponseGlobalData>()
    private val statusCorona = MutableLiveData<ApiServiceCoronaGlobal.ApiStatusCorona>()


    fun getDataPositif(): LiveData<ResponseGlobalData> = globalDataPositif
    fun getDataNegatif(): LiveData<ResponseGlobalData> = globalDataSembuh
    fun getDataMeninggal(): LiveData<ResponseGlobalData> = globalDataMeninggal
    fun getStatus(): LiveData<ApiServiceCoronaGlobal.ApiStatusCorona> = statusCorona


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestDataPositif()
            }
        }
    }

    private suspend fun requestDataPositif() {
        try {
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.LOADING)
            val resultPositif = ApiServiceCoronaGlobal.serviceDataGlobal.getPositifGlobal()
            globalDataPositif.postValue(resultPositif)
            val resultSembuh = ApiServiceCoronaGlobal.serviceDataGlobal.getSembuhGlobal()
            globalDataPositif.postValue(resultSembuh)
            val resultMeninggal = ApiServiceCoronaGlobal.serviceDataGlobal.getMeninggalGlobal()
            globalDataPositif.postValue(resultMeninggal)
            Log.d("REQUEST_GLOBAL", "Covid : $resultPositif")
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.SUCCESS)
        } catch (e: Exception) {
            statusCorona.postValue(ApiServiceCoronaGlobal.ApiStatusCorona.FAILED)
            Log.d("REQUEST_GLOBAL", e.message.toString())
        }
    }
}