package com.example.aichatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatViewModel : ViewModel() {
    private val _chatMessages = MutableLiveData<MutableList<ChatMessage>>(mutableListOf())
    val chatMessages: LiveData<MutableList<ChatMessage>> = _chatMessages

    private val db = FirebaseDatabase.getInstance().reference.child("chats")
    private val userId = FirebaseAuth.getInstance().uid ?: "anonymous"

    fun sendMessage(text: String) {
        val message = ChatMessage(userId, text)
        _chatMessages.value?.add(message)
        _chatMessages.postValue(_chatMessages.value)

        db.push().setValue(message)
        val response = ChatMessage("bot", "I'm a bot. You said: $text")
        _chatMessages.value?.add(response)
        _chatMessages.postValue(_chatMessages.value)
    }
}