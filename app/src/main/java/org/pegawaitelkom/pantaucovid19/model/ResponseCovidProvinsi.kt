package org.pegawaitelkom.pantaucovid19.model

import com.google.gson.annotations.SerializedName

data class ResponseCovidProvinsi(

	@field:SerializedName("provinsi")
	val provinsi: String? = null,

	@field:SerializedName("meninggal")
	val meninggal: Int? = null,

	@field:SerializedName("sembuh")
	val sembuh: Int? = null,

	@field:SerializedName("dirawat")
	val dirawat: Int? = null,

	@field:SerializedName("kasus")
	val kasus: Int? = null
)
