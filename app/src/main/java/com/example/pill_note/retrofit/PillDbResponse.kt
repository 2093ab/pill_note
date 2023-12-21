package com.example.pill_note.retrofit

data class PillDbResponse(
    val body: Body,
    val header: Header
) {
    data class Body(
        val items: List<Item>,
        val numOfRows: Int,
        val pageNo: Int,
        val totalCount: Int
    ) {
        data class Item(
            val atpnQesitm: String,
            val atpnWarnQesitm: Any,
            val bizrno: String,
            val depositMethodQesitm: String,
            val efcyQesitm: String,
            val entpName: String,
            val intrcQesitm: String,
            val itemImage: String,
            val itemName: String,
            val itemSeq: String,
            val openDe: String,
            val seQesitm: String,
            val updateDe: String,
            val useMethodQesitm: String
        )
    }

    data class Header(
        val resultCode: String,
        val resultMsg: String
    )
}