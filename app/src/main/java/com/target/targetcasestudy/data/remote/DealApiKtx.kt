package com.target.targetcasestudy.data.remote

import com.target.targetcasestudy.common.Constants.BASE_URL
import com.target.targetcasestudy.data.remote.dto.Deal
import com.target.targetcasestudy.data.remote.dto.DealResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiKtx {

  @GET("deals")
  suspend fun retrieveDeals(): DealResponse

  @GET("deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: String): Deal
}
