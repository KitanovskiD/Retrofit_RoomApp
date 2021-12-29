package com.example.retrofitapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.retrofitapp.api.DeezerApi
import com.example.retrofitapp.api.DeezerApiClient
import com.example.retrofitapp.database.AppDatabase
import com.example.retrofitapp.models.PlayList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var deezerApiClient: DeezerApi
    private var app: Application

    private val database: AppDatabase = AppDatabase.getInstance(application)

    private var playListMutableLiveData: MutableLiveData<PlayList>

    init {
        deezerApiClient = DeezerApiClient.getDeezerApi()!!
        app = application
        playListMutableLiveData = MutableLiveData<PlayList>()
    }


    fun searchPlayListById(id: String) {
        deezerApiClient.getPlayListById(id).enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>?, response: Response<PlayList>) {
                //displayData(response.body())
                if(response.body() != null) {
                    val currentPlayList: PlayList = response.body()

                    savePlayListInDatabase(currentPlayList)

                    playListMutableLiveData.value = currentPlayList
                }
                else{
                    Toast.makeText(app, "Error!", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<PlayList>?, t: Throwable) {
                var m = t.message
                Toast.makeText(app, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun savePlayListInDatabase(currentPlayList: PlayList) {
        CoroutineScope(Dispatchers.IO).launch {
            database.playListDao().insertPlayListWithTracks(currentPlayList, currentPlayList.tracks.data)
        }
    }

    fun getPlayListMutableLiveData(): MutableLiveData<PlayList> {
        return playListMutableLiveData
    }

}