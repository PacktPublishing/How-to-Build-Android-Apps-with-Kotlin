package com.android.testable.viewmodellivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TotalsViewModel : ViewModel() {

    private val total = MutableLiveData<Int>()

    init {
        total.postValue(0)
    }

    fun increaseTotal() {
        total.postValue((total.value ?: 0) + 1)
    }

    fun getTotal(): LiveData<Int> {
        return total
    }
}