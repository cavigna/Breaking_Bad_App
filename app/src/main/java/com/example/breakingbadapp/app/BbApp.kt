package com.example.breakingbadapp.app

import android.app.Application
import com.example.breakingbadapp.db.BaseDeDatos
import com.example.breakingbadapp.network.ApiService
import com.example.breakingbadapp.viewmodel.Repositorio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BbApp : Application() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val database  by lazy { BaseDeDatos.getDataBase(this) }

    val repositorio by lazy { Repositorio(retrofit, database.dao()) }
}