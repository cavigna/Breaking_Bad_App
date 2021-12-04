package com.example.breakingbadapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotes_tabla")
data class Quotes(
    @PrimaryKey
    @SerializedName("quote_id")
    var id: Int = 0,
    @SerializedName("author")
    var author: String = "",
    @SerializedName("quote")
    var quote: String = "",
    @SerializedName("series")
    var series: String = ""
)