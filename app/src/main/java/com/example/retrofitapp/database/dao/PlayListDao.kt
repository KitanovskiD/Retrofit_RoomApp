package com.example.retrofitapp.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.PlayList
import java.lang.Exception

abstract class PlayListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlayList(playList: PlayList)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTracks(data: MutableList<Data>)


    fun insertPlayListWithTracks(playList: PlayList, tracks: MutableList<Data>) {
        try {
            insertPlayList(playList)
            for (track in tracks) {
                track.playListId=playList.id
            }

            insertTracks(tracks)
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}