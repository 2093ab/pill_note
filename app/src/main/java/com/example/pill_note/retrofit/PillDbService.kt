package com.example.pill_note.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface PillDbService {
    @GET("getDrbEasyDrugList")
    fun getDrugList(
        @Query("ServiceKey") serviceKey: String,
        @Query("type") typeName: String = "json",
        @Query("numOfRows") numOfRows: Int = 10,
        @Query("pageNo") pageNo: Int = 1
    ): retrofit2.Call<PillDbResponse>
}