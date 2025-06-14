package com.example.tema2.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @Embedded
    @SerializedName("address")
    val address: Address
)