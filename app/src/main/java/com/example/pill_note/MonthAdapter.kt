package com.example.pill_note

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pill_note.databinding.FragmentCalendarBinding
import com.example.pill_note.databinding.MonthRecyclerviewBinding
import java.util.Calendar
import java.util.Date

class MonthViewHolder(val binding: MonthRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MonthAdpater(var parentBinding: FragmentCalendarBinding): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var calendar: Calendar = Calendar.getInstance()

    override fun getItemCount(): Int = Int.MAX_VALUE / 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
        RecyclerView.ViewHolder
        = MonthViewHolder(MonthRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MonthViewHolder).binding

        //달 구하기
        calendar.time = Date() //현재 날짜 초기화
        calendar.set(Calendar.DAY_OF_MONTH,1) //스크롤시 현재 월의 1일로 이동
        calendar.add(Calendar.MONTH, position) //스크롤시 포지션 만큼 달이동

        // 현재 날짜 출력
        parentBinding.date.setText("${calendar.get(Calendar.MONTH) + 1}월")

        val tempMonth = calendar.get(Calendar.MONTH)

        //6주 7일로 날짜를 표시
        var dayList: MutableList<Date> = MutableList(6 * 7 ) { Date() }

        for(i in 0..5) { //주
            for (k in 0..6) { //요일
                //각 달의 요일만큼 캘린더에 보여진다
                //요일 표시
                calendar.add(Calendar.DAY_OF_MONTH, (1 - calendar.get(Calendar.DAY_OF_WEEK)) + k)
                dayList[i * 7 + k] = calendar.time //배열 인덱스 만큼 요일 데이터 저장
            }
            //주 표시
            calendar.add(Calendar.WEEK_OF_MONTH, 1)
        }


        binding.monthRecyclerview.layoutManager = GridLayoutManager(binding.monthRecyclerview.context, 7)
        binding.monthRecyclerview.adapter = DayAdapter(tempMonth, dayList)
    }

}