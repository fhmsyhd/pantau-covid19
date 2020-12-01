package org.pegawaitelkom.pantaucovid19.view.berita

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.pegawaitelkom.pantaucovid19.constant.Constan.API_KEY
import org.pegawaitelkom.pantaucovid19.model.ArticlesItem
import org.pegawaitelkom.pantaucovid19.network.ApiServiceBerita
import java.lang.Exception

class BeritaViewModel :ViewModel(){
    private val newsData = MutableLiveData<List<ArticlesItem>>()
    private val status = MutableLiveData<ApiServiceBerita.ApiStatus>()

    //data status
    fun getStatus(): LiveData<ApiServiceBerita.ApiStatus> = status

    fun getDataBerita(): LiveData<List<ArticlesItem>> = newsData

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                requestDataBerita()
            }
        }
    }

    private suspend fun requestDataBerita() {
        try {
            status.postValue(ApiServiceBerita.ApiStatus.LOADING)
            val result = ApiServiceBerita.serviceBerita.getListNews("id","health",API_KEY)
            newsData.postValue(result.articles)
            Log.d("REQUEST", "Nama : ${result.articles!!.size}")
            status.postValue(ApiServiceBerita.ApiStatus.SUCCESS)
        } catch (e: Exception) {
            status.postValue(ApiServiceBerita.ApiStatus.FAILED)
            Log.d("REQUEST", e.message.toString())
        }
    }
}