package com.example.retrofitpractice.retrofit

import android.util.Log
import com.example.retrofitpractice.LoginResponse
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitManager {
    private val BASE_URL = "http://10.0.2.2:5000"
    private val TAG: String = "로그"

    companion object{
        val instance = RetrofitManager()
    }

    private val iRetrofit: IRetrofit? = RetrofitClient.getClient(BASE_URL)?.create(IRetrofit::class.java)

    fun login(username: String, password: String, completion: (LoginResponse, String) -> Unit){
        var call = iRetrofit?.loginRequest(username, password) ?: return

        call.enqueue(object: Callback<JsonElement>{
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure: ");
                completion(LoginResponse.FAIL, t.toString())
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse: ${response.body()} ");
                Log.d(TAG, "RetrofitManager - onResponse: status code is ${response.code()}");
                if(response.code() != 200){
                    completion(LoginResponse.FAIL, response.body().toString())
                }else{
                    completion(LoginResponse.OK, response.body().toString())
                }
            }
        })
    }
}