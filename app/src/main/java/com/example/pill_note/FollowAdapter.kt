package com.example.pill_note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.FollowerRecyclerviewBinding

class FollowViewHolder(val binding: FollowerRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class FollowAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = FollowViewHolder(FollowerRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FollowViewHolder).binding
        binding.followerImage.setImageResource(R.drawable.profile_icon)
        binding.followerName.text = datas[position]
    }

}