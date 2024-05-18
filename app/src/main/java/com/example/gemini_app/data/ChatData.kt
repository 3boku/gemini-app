package com.example.gemini_app.data

import android.app.VoiceInteractor.Prompt
import android.graphics.Bitmap
import com.example.gemini_app.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {

    val api_key = BuildConfig.apiKey

    suspend fun getResponse(prompt: String) : Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_key
        )

        try {
            val respone = withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }

            return Chat(
                prompt = respone.text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }

    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap) : Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey = api_key
        )

        try {

            val inputContent = content {
                image(bitmap)
                text(prompt)
            }
            val respone = withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }

            return Chat(
                prompt = respone.text ?: "error",
                bitmap = null,
                isFromUser = false
            )
        } catch (e: Exception) {
            return Chat(
                prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}