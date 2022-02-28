package com.example.retrofitproject.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitproject.Models.Album
import com.example.retrofitproject.databinding.AlbumItemBinding

class AlbumAdapter(
    private val albumsList: ArrayList<Album>
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.txtTitle.text = albumsList[position].title
        holder.binding.txtAlbumId.text = albumsList[position].id.toString()
    }

    override fun getItemCount() = albumsList.size

    inner class AlbumViewHolder(val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}