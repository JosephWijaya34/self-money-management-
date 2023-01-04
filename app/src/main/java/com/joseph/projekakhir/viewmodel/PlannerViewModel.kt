package com.joseph.projekakhir.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.projekakhir.model.Plan
import com.joseph.projekakhir.repository.PlannerRepository
import com.joseph.projekakhir.view.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(private val repository: PlannerRepository) :
    ViewModel() {
    //    add planner
    fun addPlaner(id_user: String, name: String, price: String, time: String)=repository.addPlanner(id_user, name, price, time)

    //   update planner
    fun updatePlan(id: String, name: String, price: String, time: String)=repository.updatePlan(id, name, price, time)

    //get planner data
    val _plan: MutableLiveData<Plan> by lazy {
        MutableLiveData<Plan>()
    }
    val plan: LiveData<Plan> get()=_plan
    fun getPlan()=viewModelScope.launch {
        repository.getPlanner(MainActivity.login_id).let { response ->
            if (response.isSuccessful) {
                _plan.postValue(response.body() as Plan)
            } else {
                Log.e("Get Data", "Failed!")
            }
        }
    }

//    delete plan
    fun deletePlan(id: String)=repository.deletePlan(id)
}

