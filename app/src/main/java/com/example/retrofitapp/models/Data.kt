package com.example.retrofitapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Data {
    @PrimaryKey
    val id: Long
    val title: String

    var playListId: Long
        get() = playListId
        set(value) {playListId = value }


    constructor(id: Long, title: String) {
        this.id = id
        this.title = title
    }


}