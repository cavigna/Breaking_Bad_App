package com.example.breakingbadapp.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "personajes_tabla")
data class Personaje(

    @PrimaryKey
    @SerializedName("char_id")
    var id: Int = 0,


    @Ignore
    @SerializedName("appearance")
    var appearance: List<Any> = listOf(),
    @SerializedName("better_call_saul_appearance")
    var betterCallSaulAppearance: List<Int> = listOf(),
    @SerializedName("birthday")
    var birthday: String = "",
    @SerializedName("category")
    var category: String = "",


    @SerializedName("img")
    var img: String = "https://pics.filmaffinity.com/breaking_bad_tv_series-504442815-large.jpg",
    @SerializedName("name")
    var name: String = "Personaje",
    @SerializedName("nickname")
    var nickname: String = "",
    @SerializedName("occupation")
    var occupation: List<String> = listOf(),
    @SerializedName("portrayed")
    var portrayed: String = "",
    @SerializedName("status")
    var status: String = ""
)