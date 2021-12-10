package com.example.retrofitapp.models

class PlayList {
    val id: Long
    val title: String
    val description: String
    val picture: String
    val tracks: Track

    constructor(id: Long, title: String, description: String, picture: String, tracks: Track) {
        this.id = id
        this.title = title
        this.description = description
        this.picture = picture
        this.tracks = tracks
    }
}