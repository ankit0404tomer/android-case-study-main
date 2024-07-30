package com.target.targetcasestudy.domain.repository

import com.target.targetcasestudy.data.remote.dto.Deal
import com.target.targetcasestudy.data.remote.dto.DealResponse

interface DealRepository {
    suspend fun retrieveDeals(): DealResponse
    suspend fun retrieveDeal(dealId: String): Deal
}