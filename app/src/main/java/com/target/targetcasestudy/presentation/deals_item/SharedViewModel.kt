package com.target.targetcasestudy.presentation.deals_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.remote.dto.Deal


class SharedViewModel : ViewModel() {

    private val _selectedItem = MutableLiveData<Deal>()
    val selectedItem: LiveData<Deal> get() = _selectedItem

    fun selectItem(item: Deal) {
        _selectedItem.value = item
    }
}