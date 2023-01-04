package com.joseph.projekakhir.repository

import com.joseph.projekakhir.retrofit.EndPointApi
import javax.inject.Inject

class EndPointRepository @Inject constructor(private val api: EndPointApi) {
//    add user
    fun addUser(email:String,username:String,image:String,password:String) =
        api.addUser(email,username,image,password)

//    update user
    fun updateUser(id:String,email:String,username:String,image:String,password:String) =
        api.updateUser(id,email,username,image,password)

//  check login
    fun loginUser(email:String,password:String) = api.loginUser(email,password)

//    get user id
    suspend fun getUserById(id:Int) = api.getUserById(id)
}