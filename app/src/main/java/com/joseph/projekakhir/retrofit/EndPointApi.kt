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
    ): Call<SubmitRegister>

    @FormUrlEncoded
    @PATCH("user")
    fun updateUser(
        @Field("id") id: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("image") image: String,
        @Field("password") password: String
    ): Call<Users>

    //   get user by id
    @GET("user/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): Response<Users>

    //currency
    @GET("https://api.frankfurter.app/latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<Currency>

    //cek login
    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SubmitLogin>

    //    planner api
    @GET("plan/{id}")
    suspend fun ambilPlan(
        @Path("id") id: Int
    ): Response<Plan>

    @FormUrlEncoded
    @PATCH("plan")
    fun updatePlan(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("time") time: String
    ): Call<UpdatePlanner>

    @FormUrlEncoded
    @POST("plan")
    fun addPlan(
        @Field("id_user") id_user: String,
        @Field("name") name: String,
        @Field("price") price: String,
        @Field("time") time: String
    ): Call<AddPlanner>

//    delete plan
    @DELETE("plan")
    fun deletePlan(
        @Query("id") id: String
    ): Call<UpdatePlanner>

//    create tabel uang
    @FormUrlEncoded
    @POST("pemasukan")
    fun addPemasukan(
        @Field("id_user") id_user: String,
        @Field("total_money") total_money: String,
        @Field("note") note: String,
        @Field("status") status: String
    ): Call<AddPemasukan>

//    get uang pemasukan by id
    @GET("moneyPemasukan/{id}")
    suspend fun ambilUangPemasukan(
        @Path("id") id: Int
    ): Response<Money>

//    get uang pengeluaran by id
    @GET("moneyPengeluaran/{id}")
    suspend fun ambilUangPengeluaran(
        @Path("id") id: Int
    ): Response<Money>

//    get jumlah pemasukan by id
    @GET("moneyTotalPemasukan/{id}")
    suspend fun ambilSemuaPemasukan(
        @Path("id") id: Int
    ): Response<DataMoney>

//    get jumlah pengeluaran by id
    @GET("moneyTotalPengeluaran/{id}")
    suspend fun ambilSemuaPengeluaran(
        @Path("id") id: Int
    ): Response<DataMoney>

}