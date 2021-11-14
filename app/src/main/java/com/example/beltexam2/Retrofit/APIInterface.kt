package com.example.beltexam2.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    //get the user info
    @GET("/search")
    fun getAllUniversities(@Query("name")uniName:String): Call<ArrayList<University_APIItem>>


}