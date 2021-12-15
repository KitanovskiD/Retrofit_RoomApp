package com.example.retrofitapp.database.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.PlayList

data class PlayListWithTracks (
    @Embedded
    val playList: PlayList,
    @Relation(
        parentColumn = "id",
        entityColumn = "playListId"
    )
    val tracks: MutableList<Data>
)