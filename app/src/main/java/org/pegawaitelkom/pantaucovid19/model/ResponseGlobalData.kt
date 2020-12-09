package org.pegawaitelkom.pantaucovid19.model

import com.google.gson.annotations.SerializedName

data class ResponseGlobalData(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)
