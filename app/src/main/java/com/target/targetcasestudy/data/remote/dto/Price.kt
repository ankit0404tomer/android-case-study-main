package com.target.targetcasestudy.data.remote.dto

import com.google.gson.annotations.SerializedName


data class Price(

    @SerializedName( "amount_in_cents") var amountInCents: Int? = null,
    @SerializedName( "currency_symbol") var currencySymbol: String? = null,
    @SerializedName( "display_string") var displayString: String? = null
)
