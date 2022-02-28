package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitproject.Adapters.AlbumAdapter
import com.example.retrofitproject.Adapters.UserAdapter
import com.example.retrofitproject.Models.Album
import com.example.retrofitproject.Network.API
import com.example.retrofitproject.databinding.ActivityAlbumBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val userID = intent.getIntExtra("userID", -1).toString()

        API.GetResponse().getAlbums(userID).enqueue(object : Callback<ArrayList<Album>> {
            override fun onResponse(
                call: Call<ArrayList<Album>>,
                response: Response<ArrayList<Album>>
            ) {
                if(response.isSuccessful){
                    val albums = response.body()
                    val adapter = AlbumAdapter(albums!!)
                    binding.albumsRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Album>>, t: Throwable) {
                Toast.makeText(applicationContext, ""+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}