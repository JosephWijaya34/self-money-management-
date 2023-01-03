package com.joseph.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.projekakhir.model.Plan
import com.joseph.projekakhir.repository.PlannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(private val repository: PlannerRepository) :
    ViewModel() {
    //    add planner
    fun AddPlaner(id_user: Int, name: String, price: Int, time: Int)=repository.addPlanner(id_user, name, price, time)

    //get now playing data
    val _plan: MutableLiveData<Plan> by lazy {
        MutableLiveData<Plan>()
    }
    val plan: LiveData<Plan> get()=_plan
    fun getPlan()=viewModelScope.launch {
        repository.getPlanner().let { response ->
            if (response.isSuccessful) {
                _plan.postValue(response.body() as Plan)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }


}

