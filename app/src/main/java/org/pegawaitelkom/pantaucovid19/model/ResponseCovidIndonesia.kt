package org.pegawaitelkom.pantaucovid19.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseCovidIndonesia(

	@field:SerializedName("penambahan")
	val penambahan: Penambahan? = null,

	@field:SerializedName("total")
	val total: Total? = null,

	@field:SerializedName("data")
	val data: Data? = null
): Serializable

data class Penambahan(

	@field:SerializedName("meninggal")
	val meninggal: Int? = null,

	@field:SerializedName("positif")
	val positif: Int? = null,

	@field:SerializedName("sembuh")
	val sembuh: Int? = null,

	@field:SerializedName("dirawat")
	val dirawat: Int? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null
)

data class Total(

	@field:SerializedName("meninggal")
	val meninggal: Int? = null,

	@field:SerializedName("positif")
	val positif: Int? = null,

	@field:SerializedName("sembuh")
	val sembuh: Int? = null,

	@field:SerializedName("dirawat")
	val dirawat: Int? = null,

	@field:SerializedName("lastUpdate")
	val lastUpdate: String? = null
)

data class Data(

	@field:SerializedName("total_spesimen")
	val totalSpesimen: Int? = null,

	@field:SerializedName("total_spesimen_negatif")
	val totalSpesimenNegatif: Int? = null,

	@field:SerializedName("odp")
	val odp: Int? = null,

	@field:SerializedName("pdp")
	val pdp: Int? = null
)
