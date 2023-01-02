package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class EndPointRepository @Inject constructor(private val api: EndPointApi) {
    fun addUser( email:String,username:String,image:String,password:String) =
        api.addUser(email,username,image,password)

    fun loginUser(email:String,password:String) = api.loginUser(email,password)

    suspend fun getUserById(id:Int) = api.getUserById(id)
}