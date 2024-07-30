package com.target.targetcasestudy.presentation.deals_list

import com.target.targetcasestudy.data.remote.dto.Deal

data class DealsListState(
    val isLoading: Boolean = false,
    val deals: ArrayList<Deal>? = arrayListOf(),
    val error: String = ""
)