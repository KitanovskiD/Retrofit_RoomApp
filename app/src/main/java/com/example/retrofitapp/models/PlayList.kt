package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class PlayList {
    @PrimaryKey
    val id: Long
    val title: String
    val description: String
    val picture: String

    @Ignore
    val tracks: Track


    constructor(id: Long, title: String, description: String, picture: String, tracks: Track) {
        this.id = id
        this.title = title
        this.description = description
        this.picture = picture
        this.tracks = tracks
    }
}