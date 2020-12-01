package org.pegawaitelkom.pantaucovid19.view.berita

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_berita.view.*
import org.pegawaitelkom.pantaucovid19.R
import org.pegawaitelkom.pantaucovid19.constant.Constan
import org.pegawaitelkom.pantaucovid19.databinding.FragmentBeritaBinding
import org.pegawaitelkom.pantaucovid19.model.ArticlesItem
import org.pegawaitelkom.pantaucovid19.network.ApiServiceBerita
import org.pegawaitelkom.pantaucovid19.util.AdapterUtil
import org.pegawaitelkom.pantaucovid19.util.convertWaktuNews


class BeritaFragment : Fragment() {

    lateinit var binding: FragmentBeritaBinding
    private lateinit var viewModel: BeritaViewModel
    private lateinit var newsAdapter: AdapterUtil<ArticlesItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentBeritaBinding.inflate(inflater, container, false)

        //init viewmodel
        viewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)

        viewModel.getDataBerita().observe(viewLifecycleOwner, Observer {


            binding.tvJudul.text = it[0].title

            binding.tvDeskripsi.text = it[0].description

            binding.tvTanggal.text = convertWaktuNews(it[0].publishedAt!!)

            Glide.with(requireContext())
                .load(it[0].urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .dontAnimate()
                .into(binding.imageNews2)


            newsAdapter = AdapterUtil(R.layout.item_list_berita, it, { position, itemView, item ->
                itemView.tv_judulnews.text = item.title

                itemView.tv_deskripsi_news.text = item.description

                itemView.tv_tanggal_news.text = convertWaktuNews(item.publishedAt!!)

                Glide.with(requireContext())
                    .load(item.urlToImage)
                    .dontAnimate()
                    .into(itemView.imageViewListNews)


                //view image
            }, { _, item ->
                val intent = Intent(requireContext(), DetailBeritaActivity::class.java)
                intent.putExtra(Constan.INTENT_DETAIL, item)
                startActivity(intent)
            })

            binding.rvNews.apply {
                this.layoutManager = LinearLayoutManager(requireContext())
                this.adapter = this@BeritaFragment.newsAdapter
            }
        })
        //untuk view model progress bar nya
        viewModel.getStatus().observe(requireActivity(), Observer {

            //it adalah data dari view model
            updateProgress(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    //system loading
    private fun updateProgress(status: ApiServiceBerita.ApiStatus) {
        when (status) {
            ApiServiceBerita.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiServiceBerita.ApiStatus.SUCCESS -> {
                binding.progressBar
                    .visibility = View.GONE
            }
            ApiServiceBerita.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

}