package com.example.pill_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pill_note.databinding.ActivityChatBinding
import com.example.pill_note.retrofit.PillDbResponse
import com.example.pill_note.retrofit.PillDbService
import com.example.pill_note.retrofit.RetrofitConnection

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        binding.chatWebView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        binding.chatWebView.loadUrl("https://pill-note.vercel.app/chat")
        Log.d("chat", "chat")*/

        // adapt recyclerView
        val chatList = mutableListOf<String>("sss", "aaa")
        val chatAdapter = ChatAdapter(chatList)
        binding.chatRecyclerView.adapter = chatAdapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)

        val retrofitAPI = RetrofitConnection.getInstance().create(PillDbService::class.java)
        val call = retrofitAPI.getDrugList(
            "cubY0PcNbLZlcEcfxWFNsuQzX46CBnP3TF4FQ5BdpdI7IYXE%2Fvd%2FVWiv46dkYpu7bKqyJqxqQFHo%2FiLlvUU6Qw%3D%3D",
            "json",
            10,
            1
        )
        call.enqueue(object : retrofit2.Callback<PillDbResponse> {
            override fun onResponse(
                call: retrofit2.Call<PillDbResponse>,
                response: retrofit2.Response<PillDbResponse>
            ) {
                Log.d("pill_note", "onResponse")
                if (response.isSuccessful) {
                    Log.d("pill_note", "isSuccessful")
                    val body = response.body()
                    if (body != null) {
                        Log.d("pill_note", body.toString())
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<PillDbResponse>, t: Throwable) {
                Log.d("pill_note", "onFailure")
                Log.d("pill_note", t.message.toString())
            }
        })
    }
}