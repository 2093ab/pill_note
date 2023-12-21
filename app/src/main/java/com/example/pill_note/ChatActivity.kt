package com.example.pill_note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pill_note.databinding.ActivityChatBinding

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

    }
}