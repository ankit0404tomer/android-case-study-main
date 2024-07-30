package com.target.targetcasestudy.data.remote.dto

import com.google.gson.annotations.SerializedName


data class DealResponse(
  @SerializedName( "products")
  var deals : ArrayList<Deal> = arrayListOf()
)
