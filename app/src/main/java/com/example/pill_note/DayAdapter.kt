package com.example.pill_note

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.DayRecyclerviewBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Date

private val db = Firebase.database

class DayViewHolder(val binding: DayRecyclerviewBinding, val tempMonth: Int): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.dayRecyclerview.setOnClickListener() {
            if (binding.dayText.alpha != 0.4f) {
                //val builder: AlertDialog.Builder = Builder(this.binding.root.context)

                //builder.setTitle("${tempMonth+1}월 ${binding.dayText.text}일")

                var i = 0

                if (i == 0) {
                    binding.dayPillIcon.setImageResource(R.drawable.pill_icon_color)
                    i = 1
                } else {
                    binding.dayPillIcon.setImageResource(R.drawable.pill_icon_white)
                    i = 0
                }
            }
        }
    }
}

class DayAdapter(val tempMonth: Int, val dayList: MutableList<Date>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ROW = 6

    override fun getItemCount(): Int = ROW * 7

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = DayViewHolder(DayRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false), tempMonth)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as DayViewHolder).binding

        //날짜 표시
        binding.dayText.text = dayList[position].date.toString()

        if(tempMonth != dayList[position].month) {
            binding.dayText.alpha=0.4f
            binding.dayPillIcon.alpha=0.4f
        }

        /*
        //토요일이면 파란색 || 일요일이면 빨간색으로 색상표시
        if((position + 1) % 7 == 0) {
            binding.dayText.setTextColor(ContextCompat.getColor(binding.dayRecyclerview.context, R.color.blue))
        } else if (position == 0 || position % 7 == 0) {
            binding.dayText.setTextColor(ContextCompat.getColor(binding.dayRecyclerview.context, R.color.red))
        }
        */
    }

}