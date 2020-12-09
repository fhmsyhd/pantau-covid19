package org.pegawaitelkom.pantaucovid19.view.global

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import org.pegawaitelkom.pantaucovid19.R
import org.pegawaitelkom.pantaucovid19.constant.Constan
import org.pegawaitelkom.pantaucovid19.databinding.ActivityCovidGlobalBinding
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidGlobal
import org.pegawaitelkom.pantaucovid19.view.home.HomeViewModel
import retrofit2.Response

class CovidGlobalActivity : AppCompatActivity() {
    private lateinit var viewModelCovid: CovidGlobalViewModel
    private lateinit var binding: ActivityCovidGlobalBinding
    private lateinit var mapboxMap: MapboxMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init Mapbox
        Mapbox.getInstance(this, getString(R.string.mapbox_token))

        binding = ActivityCovidGlobalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init View Model
        viewModelCovid = ViewModelProvider(this).get(CovidGlobalViewModel::class.java)



        //mapbox
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync{mapboxMap->
            this.mapboxMap= mapboxMap
            getDataDunia()
        }
    }

    private fun getDataDunia() {
        viewModelCovid.getDataIndonesia().observe(this,{
            showPinMap(it)
        })
    }

    private fun showPinMap(dataGlobal:List<ResponseCovidGlobal>){
        mapboxMap.setStyle(Style.MAPBOX_STREETS){style->
            style.addImage("ICON_ID",BitmapFactory.decodeResource(resources,R.drawable.mapbox_marker_icon_default))
            val latLngBoundsBuilder = LatLngBounds.Builder()

            val symbolManager = SymbolManager(binding.mapView,mapboxMap,style)
            symbolManager.iconAllowOverlap=true

            val options = ArrayList<SymbolOptions>()
            dataGlobal.forEach { data->
                if (data.attributes?.lat !=null&&data.attributes?.long !=null) {
                    latLngBoundsBuilder.include(
                        LatLng(
                            data.attributes?.lat!!,
                            data.attributes.long!!
                        )
                    )
                    options.add(
                        SymbolOptions()
                            .withLatLng(LatLng(data.attributes.lat, data.attributes.long))
                            .withIconImage("ICON_ID")
                            .withData(Gson().toJsonTree(data))
                    )
                }
            }
            symbolManager.create(options)

            val latLngBound = latLngBoundsBuilder.build()
            mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(latLngBound,50),5000)

            symbolManager.addClickListener { symbol->
                val data = Gson().fromJson(symbol.data,ResponseCovidGlobal::class.java)
                val intent = Intent(this , DetailCovidGlobalActivity::class.java)
                intent.putExtra(Constan.INTENT_DETAIL,data)
                startActivity(intent)
            }
        }
    }


    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

}