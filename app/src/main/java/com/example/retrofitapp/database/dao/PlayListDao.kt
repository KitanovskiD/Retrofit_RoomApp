package com.example.retrofitapp.database.dao

import androidx.room.*
import com.example.retrofitapp.database.relationship.PlayListWithTracks
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.PlayList
import java.lang.Exception

@Dao
abstract class PlayListDao {

    @Transaction
    @Query("SELECT * FROM PlayList WHERE id = :id")
    abstract fun getPlayListWithTracks(id: Long) : PlayListWithTracks?

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