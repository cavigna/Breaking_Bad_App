package com.example.breakingbadapp.utils

import androidx.room.TypeConverter

class Converters {

    @TypeConverter

    fun fromListadoAString(listado: List<String?>):String?{
        return listado.joinToString {
            it.toString()
        }
    }

    @TypeConverter
    fun fromStringToListado(string: String):List<String>{
        return string.split(",")
    }

    @TypeConverter
    fun fromListadoIntToString(list: List<Int>):String{
        return list.joinToString {
            it.toString()
        }
    }

    @TypeConverter
    fun fromStringToListadoInt(string: String?):List<Int?>?{
        val numeros = string?.split(",")

        return numeros?.map { it.toIntOrNull() }
    }
}