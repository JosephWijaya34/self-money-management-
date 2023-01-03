package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class PlannerRepository @Inject constructor(private val api: EndPointApi) {
    //    buat read plan
    suspend fun getPlanner()=api.ambilPlan()

    //    add plan
    fun addPlanner(id_user: Int, name: String, price: Int, time: Int)=
        api.addPlan(id_user, name, price, time)
}

