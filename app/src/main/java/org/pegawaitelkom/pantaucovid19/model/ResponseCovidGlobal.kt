package org.pegawaitelkom.pantaucovid19.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseCovidGlobal(

	@field:SerializedName("attributes")
	val attributes: Attributes? = null
):Serializable

data class Attributes(

	@field:SerializedName("OBJECTID")
	val oBJECTID: Int? = null,

	@field:SerializedName("Long_")
	val long: Double? = null,

	@field:SerializedName("Recovered")
	val recovered: Int? = null,

	@field:SerializedName("Country_Region")
	val countryRegion: String? = null,

	@field:SerializedName("Active")
	val active: Int? = null,

	@field:SerializedName("Last_Update")
	val lastUpdate: Long? = null,

	@field:SerializedName("Deaths")
	val deaths: Int? = null,

	@field:SerializedName("Confirmed")
	val confirmed: Int? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null
):Serializable
