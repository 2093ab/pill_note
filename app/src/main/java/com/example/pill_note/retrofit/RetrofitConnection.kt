package com.example.pill_note.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        private const val BASE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create(gson)
                    )
                    .build()
            }
            return INSTANCE!!
        }
    }
}