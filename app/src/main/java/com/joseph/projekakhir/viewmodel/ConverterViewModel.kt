package com.joseph.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.projekakhir.model.Currency
import com.joseph.projekakhir.model.Rates
import com.joseph.projekakhir.repository.ConverterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(private val repository: ConverterRepository) : ViewModel() {
    val _rate: MutableLiveData<Currency> by lazy {
        MutableLiveData<Currency>()
    }
    val rate: LiveData<Currency> get()=_rate

    fun getRate()= viewModelScope.launch {
        repository.getCurrency().let { response ->
            if (response.isSuccessful) {
                _rate.postValue(response.body() as Currency)
            } else {
                Log.e("Get Currency", "Failed!")
            }
        }
    }
}