package com.example.aichatbot

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aichatbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ChatViewModel by viewModels()
    private val chatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = chatAdapter

        viewModel.chatMessages.observe(this, Observer {
            chatAdapter.submitList(it.toList())
            binding.recyclerView.scrollToPosition(it.size - 1)
        })

        binding.sendButton.setOnClickListener {
            val message = binding.messageInput.text.toString()
            if (message.isNotBlank()) {
                viewModel.sendMessage(message)
                binding.messageInput.text.clear()
            }
        }
    }
}