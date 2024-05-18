package com.example.gemini_app

import android.graphics.Bitmap
import com.example.gemini_app.data.Chat

sealed class ChatUIEvent {
    data class UpdatePrompt(val newPrompt: String): ChatUIEvent()
    data class SendPrompt(
        val prompt: String,
        val bitmap: Bitmap?
    ): ChatUIEvent()
}