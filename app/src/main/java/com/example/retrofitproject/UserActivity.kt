package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitproject.Adapters.UserAdapter
import com.example.retrofitproject.Models.User
import com.example.retrofitproject.Network.API
import com.example.retrofitproject.databinding.ActivityUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        API.GetResponse().getUsers().enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if(response.isSuccessful){
                    val users = response.body()
                    val adapter = UserAdapter(users!!, applicationContext)
                    binding.userRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Toast.makeText(applicationContext, ""+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}