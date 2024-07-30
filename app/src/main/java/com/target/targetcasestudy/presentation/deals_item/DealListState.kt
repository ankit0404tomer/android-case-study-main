package com.target.targetcasestudy.presentation.deals_item

import com.target.targetcasestudy.data.remote.dto.Deal

data class DealListState(
    val isLoading: Boolean = false,
    val deals: Deal?=null,
    val error: String = ""
)