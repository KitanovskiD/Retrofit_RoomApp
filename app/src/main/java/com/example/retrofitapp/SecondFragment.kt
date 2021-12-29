package com.example.retrofitapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.adapters.TrackAdapter
import com.example.retrofitapp.database.relationship.PlayListWithTracks
import com.example.retrofitapp.models.Data
import com.example.retrofitapp.viewmodel.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var secondFragmentViewModel: SecondFragmentViewModel

    private lateinit var trackRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: TrackAdapter
    private lateinit var plTitle: TextView
    private lateinit var plImage: ImageView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondFragmentViewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)

        secondFragmentViewModel.loadDataFromDatabase(3773404202)

        secondFragmentViewModel.getPlayListWithTracksMutableLiveData()
            .observe(viewLifecycleOwner, object : Observer<PlayListWithTracks?>{
                override fun onChanged(data: PlayListWithTracks?) {
                    if(data != null) {
                        displayDataFromDatabase(data)
                    }
                    else {
                        Toast.makeText(activity, "Error!!", Toast.LENGTH_LONG).show()
                    }
                }

            })

        plTitle = view.findViewById(R.id.plId)

        plImage = view.findViewById(R.id.plImage)

        trackRecyclerView = view.findViewById(R.id.databasePlayList)

        trackRecyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerViewAdapter = TrackAdapter(mutableListOf<Data>())

        trackRecyclerView.setHasFixedSize(true)

        trackRecyclerView.adapter = recyclerViewAdapter
    }

    private fun displayDataFromDatabase(data: PlayListWithTracks) {
        plTitle.text = data.playList.title
        Glide.with(this).load(data.playList.picture).into(plImage);
        recyclerViewAdapter.updateData(data.tracks)
    }


}