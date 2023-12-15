package com.example.pill_note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.HomeRecyclerviewBinding


class HomeViewHolder(val binding: HomeRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class HomeAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = HomeViewHolder(HomeRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as HomeViewHolder).binding
        binding.medicineCategory.drawable
        binding.medicineName.text = datas[position]
    }
}