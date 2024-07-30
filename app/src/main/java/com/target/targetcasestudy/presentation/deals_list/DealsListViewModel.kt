package com.target.targetcasestudy.presentation.deals_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.common.Resource
import com.target.targetcasestudy.domain.use_case.get_deal_item.GetDealListUseCase
import com.target.targetcasestudy.domain.use_case.get_deal_list.GetDealUseCase
import com.target.targetcasestudy.presentation.deals_item.DealListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class DealsListViewModel @Inject constructor(
    private val listUseCase: GetDealListUseCase,
    private val dealUseCase: GetDealUseCase

) : ViewModel() {
    private val _dealsListData = MutableLiveData<DealsListState>()
    val dealsListData = _dealsListData

    private val _dealListData = MutableLiveData<DealListState>()
    val dealListData = _dealListData

    init {
       getCoins()
    }

    private fun getCoins() {
        listUseCase().onEach{ result ->
            when (result) {
                is Resource.Success -> {
                    _dealsListData.value = DealsListState(deals = result.data)
                }
                is Resource.Error -> {
                    _dealsListData.value = DealsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _dealsListData.value = DealsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onItemClicked(dealsId: Int?) {
        if (dealsId != null) {
            dealUseCase(dealsId).onEach{ result ->
                when (result) {
                    is Resource.Success -> {
                        _dealListData.value = DealListState(deals = result.data)
                    }
                    is Resource.Error -> {
                        _dealListData.value = DealListState(
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _dealListData.value = DealListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }else{
            _dealListData.value = DealListState(deals = null)
        }
    }
}