package com.example.retrofitapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.database.AppDatabase
import com.example.retrofitapp.database.relationship.PlayListWithTracks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var app: Application = application

    private val database: AppDatabase = AppDatabase.getInstance(application)

    private var playListWithTracksMutableLiveData: MutableLiveData<PlayListWithTracks> = MutableLiveData<PlayListWithTracks>()

    fun loadDataFromDatabase(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val playListWithTracks = database.playListDao().getPlayListWithTracks(id)

            withContext(Dispatchers.Main) {
                playListWithTracksMutableLiveData.value = playListWithTracks
            }
        }
    }

    fun getPlayListWithTracksMutableLiveData(): MutableLiveData<PlayListWithTracks> {
        return playListWithTracksMutableLiveData
    }

}