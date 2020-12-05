package org.pegawaitelkom.pantaucovid19.view.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_provinsi.view.*
import org.marproject.reusablerecyclerviewadapter.ReusableAdapter
import org.marproject.reusablerecyclerviewadapter.interfaces.AdapterCallback
import org.pegawaitelkom.pantaucovid19.R
import org.pegawaitelkom.pantaucovid19.constant.Constan.SEARCH_HINT
import org.pegawaitelkom.pantaucovid19.databinding.FragmentHomeBinding
import org.pegawaitelkom.pantaucovid19.model.ResponseCovidProvinsi
import org.pegawaitelkom.pantaucovid19.network.ApiServiceCorona
import org.pegawaitelkom.pantaucovid19.util.AdapterUtil


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ReusableAdapter<ResponseCovidProvinsi>
    private lateinit var viewModelCovid: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = ReusableAdapter(requireContext())

        viewModelCovid = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModelCovid.getDataIndonesia().observe(viewLifecycleOwner, {
            binding.tvPositif.text = it.total?.dirawat.toString()
            binding.tvTotal.text = it.total?.positif.toString()
            binding.tvSembuh.text = it.total?.sembuh.toString()
            binding.tvMeninggal.text = it.total?.meninggal.toString()
            binding.tvDateLastUpdate.text = it.total?.lastUpdate.toString()
        })

        viewModelCovid.getDataProvinsi().observe(viewLifecycleOwner, {
            Log.i("testingOfflineProv", it.toString())
            if (it.isNotEmpty()) {
                Log.i("testing", "pengguna lama")
                //adapterHomeAdapter.addlistProvinsi(it.sortedByDescending { data -> data.Kasus_Posi })
                setupAdapter(binding.rvProvinsi, it.sortedByDescending { data -> data.kasus})
            } else {
                Log.i("testing", "pengguna baru")
                //adapterHomeAdapter.addlistProvinsi(it)
                setupAdapter(binding.rvProvinsi, it.sortedByDescending { data -> data.kasus})
            }

        })

        val searchBar = binding.searchBar
        searchBar.apply {
            setHint(SEARCH_HINT)
            setPlaceHolder(SEARCH_HINT)
            setSearchIcon(R.drawable.ic__search)
        }

        // searchbar event
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            // event ketika text berubah/diketik
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

        })

        viewModelCovid.getStatus().observe(requireActivity(), Observer {
            //it adalah data dari view model
            updateProgress(it)
        })

        return binding.root
    }

    private fun setupAdapter(recyclerView: RecyclerView, items: List<ResponseCovidProvinsi>) {
        adapter.adapterCallback(adapterCallback)
            .setLayout(R.layout.item_list_provinsi)
            .filterable()
            .addData(items)
            .isVerticalView()
            .build(recyclerView)
    }

    private val adapterCallback = object : AdapterCallback<ResponseCovidProvinsi> {
        override fun initComponent(itemView: View, data: ResponseCovidProvinsi, itemIndex: Int) {
            itemView.tv_provinsi.text = data.provinsi
            itemView.tv_positif_provinsi.text = data.kasus.toString()
            itemView.tv_sembuh_provinsi.text = data.sembuh.toString()
            itemView.tv_meninggal_provinsi.text = data.meninggal.toString()
        }

        override fun onItemClicked(itemView: View, data: ResponseCovidProvinsi, itemIndex: Int) {
            TODO("Not yet implemented")
        }
    }

    //system loading
    private fun updateProgress(status: ApiServiceCorona.ApiStatusCorona) {
        when (status) {
            ApiServiceCorona.ApiStatusCorona.LOADING -> {
                binding.progressbar.visibility = View.VISIBLE
            }
            ApiServiceCorona.ApiStatusCorona.SUCCESS -> {
                binding.progressbar.visibility = View.GONE
            }
            ApiServiceCorona.ApiStatusCorona.FAILED -> {
                binding.progressbar.visibility = View.GONE
            }
        }
    }

}