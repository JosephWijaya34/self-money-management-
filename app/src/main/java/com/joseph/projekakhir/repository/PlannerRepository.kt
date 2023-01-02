package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class PlannerRepository @Inject constructor(private val api: EndPointApi) {
    suspend fun getPlanner() = api.ambilPlan()
}

