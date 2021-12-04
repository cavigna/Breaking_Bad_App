package com.example.breakingbadapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "muertes_tabla")
data class Muertes(
    @PrimaryKey
    @SerializedName("death_id")
    var id: Int = 0,
    @SerializedName("cause")
    var cause: String = "",
    @SerializedName("death")
    var death: String = "",
    @SerializedName("episode")
    var episode: Int = 0,
    @SerializedName("last_words")
    var lastWords: String = "",
    @SerializedName("number_of_deaths")
    var numberOfDeaths: Int = 0,
    @SerializedName("responsible")
    var responsible: String = "",
    @SerializedName("season")
    var season: Int = 0
)