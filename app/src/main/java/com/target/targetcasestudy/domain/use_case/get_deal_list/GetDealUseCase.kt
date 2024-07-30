package com.target.targetcasestudy.domain.use_case.get_deal_list

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
class GetDealUseCase @Inject constructor(
    private val repository: DealRepository
) {
    operator fun invoke(dealsId: Int): Flow<Resource<Deal>> = flow {
        try {
            emit(Resource.Loading<Deal>())
            val deals = repository.retrieveDeal(dealsId.toString())
            emit(Resource.Success<Deal>(deals))
        } catch (e: HttpException) {
            emit(Resource.Error<Deal>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Deal>("Couldn't reach server. Check your internet connection."))
        }
    }

}