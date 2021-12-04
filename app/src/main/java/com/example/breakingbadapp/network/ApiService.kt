package com.example.breakingbadapp.network

import com.example.breakingbadapp.model.Episodio
import com.example.breakingbadapp.model.Muertes
import com.example.breakingbadapp.model.Personaje
import com.example.breakingbadapp.model.Quotes
import retrofit2.http.GET

interface ApiService {

    @GET("quotes")
    suspend fun listadoFrasesApi(): List<Quotes>

    @GET("characters")
    suspend fun listadoPersonajesApi(): List<Personaje>

    @GET("episodes")
    suspend fun listadoEpisodiosApi(): List<Episodio>

    @GET("deaths")
    suspend fun listadoMuertesApi(): List<Muertes>

}