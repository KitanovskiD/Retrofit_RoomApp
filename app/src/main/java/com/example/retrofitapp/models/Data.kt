package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Data (
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    var playListId: Long = 0
)