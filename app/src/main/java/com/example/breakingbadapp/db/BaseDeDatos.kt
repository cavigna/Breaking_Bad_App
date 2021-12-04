package com.example.breakingbadapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingbadapp.model.Episodio
import com.example.breakingbadapp.model.Muertes
import com.example.breakingbadapp.model.Personaje
import com.example.breakingbadapp.model.Quotes
import com.example.breakingbadapp.utils.Converters


@TypeConverters(Converters::class)
@Database(entities = [Quotes::class, Personaje::class, Episodio::class, Muertes::class],
    version = 1, exportSchema = false)
abstract class BaseDeDatos : RoomDatabase() {
    abstract fun dao() : BbDao

    companion object {

        @Volatile
        private var INSTANCE: BaseDeDatos? = null

        fun getDataBase(context: Context): BaseDeDatos {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "breaking_bad_db"
                )
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}