package org.pegawaitelkom.pantaucovid19.view.global

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.pegawaitelkom.pantaucovid19.R
import org.pegawaitelkom.pantaucovid19.constant.Constan
import org.pegawaitelkom.pantaucovid19.databinding.ActivityDetailCovidGlobalBinding
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidGlobal

class DetailCovidGlobalActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailCovidGlobalBinding
    lateinit var data: ResponseCovidGlobal
    private lateinit var viewModelCovid: DetailCovidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCovidGlobalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Init View Model
        viewModelCovid = ViewModelProvider(this).get(DetailCovidViewModel::class.java)

        dataGlobal()
        data = intent.getSerializableExtra(Constan.INTENT_DETAIL) as ResponseCovidGlobal
        initUI()
    }

    private fun initUI() {
        //Positif
        binding.tvJumlahPositif.text = data.attributes!!.confirmed.toString()
        //Negatif
        binding.tvJumlahSembuh.text = data.attributes!!.recovered.toString()
        //Meninggal
        binding.tvJumlahMeninggal.text = data.attributes!!.deaths.toString()
        //Aktif
        binding.tvJumlahAktif.text = data.attributes!!.active.toString()
        //Negara
        binding.tvNegara.text = data.attributes!!.countryRegion
    }

    private fun dataGlobal() {
        viewModelCovid.getDataPositif().observe(this, {
            binding.tvPositifGlobal.text = it.value
        })
        viewModelCovid.getDataNegatif().observe(this, {
            binding.tvNegatifGlobal.text = it.value
        })
        viewModelCovid.getDataMeninggal().observe(this, {
            binding.tvMeninggalGlobal.text = it.value
        })
    }
}