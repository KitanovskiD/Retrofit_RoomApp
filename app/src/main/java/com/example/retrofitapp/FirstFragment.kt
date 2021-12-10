package com.example.retrofitapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.adapters.TrackAdapter
import com.example.retrofitapp.api.DeezerApi
import com.example.retrofitapp.api.DeezerApiClient
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.models.PlayList
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {

    private lateinit var deezerApiClient: DeezerApi

    private lateinit var tvPlayListTitle: TextView
    private lateinit var ivPlayListPicture: ImageView
    private lateinit var trackRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TrackAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deezerApiClient = DeezerApiClient.getDeezerApi()!!

        val playListId: EditText = view.findViewById<EditText>(R.id.etPlayListId)
        tvPlayListTitle = view.findViewById(R.id.tvPlayListTitle)
        ivPlayListPicture = view.findViewById(R.id.ivPlayListPicture)

        trackRecyclerView = view.findViewById(R.id.allTracksRecyclerView)

        trackRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = TrackAdapter(mutableListOf<Data>())

        trackRecyclerView.setHasFixedSize(true)

        trackRecyclerView.adapter = recyclerViewAdapter

        playListId.setOnEditorActionListener{ v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                val listId: String = playListId.text.toString()
                searchPlayListById(listId)
                true
            }
            else {
                Toast.makeText(activity, "Error!", Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    //3773404202
    //1578812305
    private fun searchPlayListById(id: String) {
        deezerApiClient.getPlayListById(id).enqueue(object : Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>?, response: Response<PlayList>) {
                displayData(response.body())
                Toast.makeText(activity, "Success!", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<PlayList>?, t: Throwable) {
                var m = t.message
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayData(data: PlayList) {
        tvPlayListTitle.text = data.title
        Glide.with(this).load(data.picture).into(ivPlayListPicture);
        recyclerViewAdapter.updateData(data.tracks.data)
    }
}