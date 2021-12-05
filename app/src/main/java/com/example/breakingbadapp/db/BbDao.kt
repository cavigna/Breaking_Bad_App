package com.example.breakingbadapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingbadapp.model.Episodio
import com.example.breakingbadapp.model.Muertes
import com.example.breakingbadapp.model.Personaje
import com.example.breakingbadapp.model.Quotes
import kotlinx.coroutines.flow.Flow

@Dao
interface BbDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarListadoFrases(listadoFrases : List<Quotes>)

    @Query("SELECT * FROM quotes_tabla where id=:id")
    fun randomQuote(id: Int): Flow<Quotes>

    @Query("SELECT * FROM quotes_tabla")
    fun listadoFrases(): Flow<List<Quotes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarListadoPersonajes(listadoFrases : List<Personaje>)

    @Query("SELECT * FROM personajes_tabla")
    fun listadoPersonajes(): Flow<List<Personaje>>

    @Query("SELECT * FROM personajes_tabla where id=:id")
    fun personajeRandom(id: Int): Flow<Personaje>

    @Query("SELECT * FROM personajes_tabla WHERE name LIKE '%' || :search || '%'" +
            " OR nickname LIKE '%' || :search || '%'" +
            " OR portrayed LIKE '%' || :search || '%' ")
    fun buscarPersonaje(search: String): Flow<List<Personaje>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarListadoEpisodios(listadoFrases : List<Episodio>)

    @Query("SELECT * FROM episodios_tabla")
    fun listadoEpisodios(): Flow<List<Episodio>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun agregarListadoMuertes(listadoFrases : List<Muertes>)

    @Query("SELECT * FROM muertes_tabla")
    fun listadoMuertes(): Flow<List<Muertes>>

    @Query("SELECT COUNT(*) from personajes_tabla")
    fun cantidadPersonajes(): Flow<Int>


}