package com.target.targetcasestudy.di

import com.target.targetcasestudy.presentation.deals_list.DealsListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import com.target.targetcasestudy.domain.use_case.get_deal_item.GetDealListUseCase
import com.target.targetcasestudy.domain.use_case.get_deal_list.GetDealUseCase


@Module
@InstallIn(ActivityComponent::class)
object ViewModelProvider {

    @Provides
    fun provideViewModel(
        getDealListUseCase: GetDealListUseCase,
        dealUseCase : GetDealUseCase
    ): DealsListViewModel {
        return DealsListViewModel(
            getDealListUseCase,
            dealUseCase
        )
    }
}