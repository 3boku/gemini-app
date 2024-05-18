package com.example.gemini_app

import android.app.VoiceInteractor.Prompt
import android.graphics.Bitmap
import com.example.gemini_app.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt:  String = "",
    val bitmap: Bitmap? = null
)