package com.example.rekamjejakkotlin.model


import com.google.gson.annotations.SerializedName


data class ResponseCountry(

	@field:SerializedName("meninggal")
	val meninggal: String? = null,

	@field:SerializedName("positif")
	val positif: String? = null,

	@field:SerializedName("sembuh")
	val sembuh: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)