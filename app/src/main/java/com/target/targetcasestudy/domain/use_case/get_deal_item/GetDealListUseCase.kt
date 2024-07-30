package com.target.targetcasestudy.domain.use_case.get_deal_item

import com.target.targetcasestudy.common.Resource
import com.target.targetcasestudy.data.remote.dto.Deal
import com.target.targetcasestudy.domain.repository.DealRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class GetDealListUseCase @Inject constructor(
    private val repository: DealRepository
) {
    operator fun invoke(): Flow<Resource<ArrayList<Deal>>> = flow {
        try {
            emit(Resource.Loading<ArrayList<Deal>>())
            val deals = repository.retrieveDeals().deals
            emit(Resource.Success<ArrayList<Deal>>(deals))
        } catch (e: HttpException) {
            emit(Resource.Error<ArrayList<Deal>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<ArrayList<Deal>>("Couldn't reach server. Check your internet connection."))
        }
    }

}