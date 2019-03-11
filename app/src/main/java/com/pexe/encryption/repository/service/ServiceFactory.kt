package com.pexe.encryption.repository.service

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory {

    companion object {

        private const val BASE_URL = "https://movida-premium-mock.herokuapp.com/"

        private val certificatePinner = CertificatePinner.Builder()
            .add(
                "movida-premium-mock.herokuapp.com",
                "sha256/Vuy2zjFSPqF5Hz18k88DpUViKGbABaF3vZx5Raghplc="
            )
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().certificatePinner(certificatePinner).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getCarsService() = retrofit.create(CarsService::class.java)

    }
}