package com.ods.myunittests.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ods.myunittests.data.Spend
import com.ods.myunittests.data.SpendTrackerDataSource
import kotlinx.coroutines.launch
import java.util.*

class SpendViewModel(private val dataSource: SpendTrackerDataSource) : ViewModel() {

    private val listOfSpends = MutableLiveData<List<Spend>>()

    fun getListOfSpends(): LiveData<List<Spend>> {
        return listOfSpends
    }

    fun addSpend(amount: Int, description: String) = viewModelScope.launch {
        dataSource.addSpend(Spend(Date(), amount, description))
    }

    fun setListOfSpend() = viewModelScope.launch {
        listOfSpends.value = dataSource.getLast20Spends()
    }
}