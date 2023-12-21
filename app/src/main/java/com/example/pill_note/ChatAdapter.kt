package com.example.pill_note

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.ChatRecyclerViewBinding

class ChatViewHolder(val binding: ChatRecyclerViewBinding) :
    RecyclerView.ViewHolder(binding.root)

class ChatAdapter(val datas: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder = ChatViewHolder(
        ChatRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ChatViewHolder).binding

        /*
    //날짜 표시
    binding.dayText.text = dayList[position].date.toString()

    if (tempMonth != dayList[position].month) {
        binding.dayText.alpha = 0.4f
        binding.dayPillIcon.alpha = 0.4f
    }*/
    }

}
