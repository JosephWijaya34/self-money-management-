package com.joseph.projekakhir.repository

import com.joseph.projekakhir.model.Currency
import com.joseph.projekakhir.retrofit.EndPointApi
import com.joseph.projekakhir.util.Resource
import javax.inject.Inject

class ConverterRepository @Inject constructor(private val api: EndPointApi): ConverterRepositorys {
//    override suspend fun getRates(base: String) = Resource<ConverterRepositorys>

    override suspend fun getRates(base: String): Resource<Currency> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }


}