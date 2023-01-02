package com.joseph.projekakhir.retrofit

import com.joseph.projekakhir.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface EndPointApi {
//    users api
    @FormUrlEncoded
    @POST("user")
    fun addUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("image") image: String,
        @Field("password") password: String
//    @Query("userData") userData: DataUser
    ): Call<SubmitRegister>
//currency
@GET("https://api.frankfurter.app/latest")
suspend fun getRates(
    @Query("base") base: String
):Response<Currency>

    //cek login
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SubmitLogin>

//    planner api
    @GET("plan")
    suspend fun ambilPlan(
    ):Response<Plan>

//    currency api
    @GET("currency")
    suspend fun ambilCurrency(
    ):Response<Currency>

    //   get user by id
    @GET("user/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<Users>
}