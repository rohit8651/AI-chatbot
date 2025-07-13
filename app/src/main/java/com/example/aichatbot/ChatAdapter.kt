package com.example.aichatbot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aichatbot.databinding.ItemMessageBinding

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private val messages = mutableListOf<ChatMessage>()

    fun submitList(newMessages: List<ChatMessage>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    inner class ChatViewHolder(private val binding: ItemMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.textMessage.text = message.message
            binding.textSender.text = if (message.senderId == "bot") "Bot" else "You"
        }
    }
}