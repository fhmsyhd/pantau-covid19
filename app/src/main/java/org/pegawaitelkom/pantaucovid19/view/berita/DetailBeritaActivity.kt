package org.pegawaitelkom.pantaucovid19.view.berita

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.item_list_berita.view.*
import org.pegawaitelkom.pantaucovid19.R
import org.pegawaitelkom.pantaucovid19.constant.Constan
import org.pegawaitelkom.pantaucovid19.databinding.ActivityDetailBeritaBinding
import org.pegawaitelkom.pantaucovid19.model.ArticlesItem

class DetailBeritaActivity : AppCompatActivity() {

    companion object {
        private const val CHECK_IN_URL =
            "https://www.google.com/search?safe=strict&sxsrf=ALeKk02Lk3ITyOATFaqKv1ec_4zKsuxC5Q%3A1607189438345&source=hp&ei=vsPLX9jtEtj1rQGjop0Q&q=kesehatan&oq=kesehatan&gs_lcp=CgZwc3ktYWIQAzIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywEyBQgAEMsBMgUIABDLATIFCAAQywE6BwgjEOoCECc6BAgjECc6BQguEJECOgUIABCRAjoICAAQsQMQgwE6BQgAELEDOgUILhCxAzoHCC4QsQMQQzoECC4QQzoECAAQQzoKCC4QxwEQowIQQzoICC4QsQMQgwFQu4YEWLmSBGCflQRoAXAAeACAAcABiAHiB5IBAzQuNZgBAKABAaoBB2d3cy13aXqwAQo&sclient=psy-ab&ved=0ahUKEwjYxLqcr7ftAhXYeisKHSNRBwIQ4dUDCAc&uact=5"
    }
    private lateinit var binding: ActivityDetailBeritaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_linkNews.setOnClickListener { linkSekarang() }
        data = intent.getSerializableExtra(Constan.INTENT_DETAIL) as ArticlesItem

        binding.judulDetailNews.text = data.title
//        binding.tvPenulis.text=data.
        binding.tvIsiberita.text = data.description

    }

    private fun linkSekarang() {
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(this, Uri.parse(CHECK_IN_URL))
    }

    private lateinit var data: ArticlesItem

}