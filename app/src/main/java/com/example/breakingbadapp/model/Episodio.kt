package com.example.breakingbadapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodios_tabla")
data class Episodio(
    @PrimaryKey
    @SerializedName("episode_id")
    var episodeId: Int = 0,
    @SerializedName("air_date")
    var airDate: String = "",
    @SerializedName("characters")
    var characters: List<String> = listOf(),
    @SerializedName("episode")
    var episode: String = "",
    @SerializedName("season")
    var season: String = "",
    @SerializedName("series")
    var series: String = "",
    @SerializedName("title")
    var title: String = ""
)