package com.example.retrofitpractice.retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface IRetrofit {
    @FormUrlEncoded
    @POST("/login")
    fun loginRequest(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<JsonElement>
}