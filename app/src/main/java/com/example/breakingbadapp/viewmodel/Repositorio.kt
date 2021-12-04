package com.example.breakingbadapp.viewmodel

import com.example.breakingbadapp.db.BbDao
import com.example.breakingbadapp.network.ApiService

class Repositorio(private val api: ApiService, private val dao: BbDao) {

    private suspend fun listadoFrasesApi() = api.listadoFrasesApi()
    private suspend fun listadoPersonajesApi() = api.listadoPersonajesApi()
    private suspend fun listadoEpisodiosApi() = api.listadoEpisodiosApi()
    private suspend fun listadoMuertesApi() = api.listadoMuertesApi()

    suspend fun agregarDB(){
        dao.agregarListadoFrases(listadoFrasesApi())
        dao.agregarListadoPersonajes(listadoPersonajesApi())
        dao.agregarListadoEpisodios(listadoEpisodiosApi())
        dao.agregarListadoMuertes(listadoMuertesApi())
    }

     fun fraseRandom(id:Int = (1..102).random()) = dao.randomQuote(id)
     fun personajeRandmom(id:Int = (2..62).random()) = dao.personajeRandom(id)

    fun listadoPersonajes() = dao.listadoPersonajes()
    fun listadoFrases() = dao.listadoFrases()
    fun listadoMuertes() = dao.listadoMuertes()
}