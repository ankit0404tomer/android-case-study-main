package com.target.targetcasestudy.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Deal(

    var id: Int? = null,
    var title: String? = null,
    var aisle: String? = null,
    var description: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName( "regular_price") var regularPrice: Price? = Price(),
    @SerializedName("sale_price") var salePrice: Price? = Price(),
    var fulfillment: String? = null,
    var availability: String? = null
)
