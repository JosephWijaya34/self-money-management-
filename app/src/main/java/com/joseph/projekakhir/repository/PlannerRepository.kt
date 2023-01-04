package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class PlannerRepository @Inject constructor(private val api: EndPointApi) {
    //    buat read plan
    suspend fun getPlanner(id:Int)=api.ambilPlan(id)

    //    add plan
    fun addPlanner(id_user: String, name: String, price: String, time: String)=
        api.addPlan(id_user, name, price, time)

    //   update plan
    fun updatePlan(id: String, name: String, price: String, time: String)=
        api.updatePlan(id, name, price, time)

     //    delete plan
    fun deletePlan(id: String)=api.deletePlan(id)
}

