package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class PlayList (
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val picture: String = ""
) {
    @Ignore
    val tracks: Track = Track()
}