package com.target.targetcasestudy.data.repository

import com.target.targetcasestudy.data.remote.DealApiKtx
import com.target.targetcasestudy.data.remote.dto.Deal
import com.target.targetcasestudy.data.remote.dto.DealResponse
import com.target.targetcasestudy.domain.repository.DealRepository
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(
    private val api: DealApiKtx
) : DealRepository {
    override suspend fun retrieveDeals(): DealResponse {
        return api.retrieveDeals()
    }

    override suspend fun retrieveDeal(dealId: String): Deal {
        return api.retrieveDeal(dealId)
    }

}
