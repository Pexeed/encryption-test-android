package com.pexe.encryption.repository.service

import com.pexe.encryption.model.CarBodywork
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList


interface CarsService {

    @GET("bodyworks")
    fun getCarBodyworks(): Call<ArrayList<CarBodywork>>

}