package com.example.chatbot

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.chatbot.model.MessageModel
import com.example.chatbot.network.RemoteApiCall
import com.example.chatbot.ui.theme.ChatBotTheme
import com.example.chatbot.uiScreen.ChatScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    companion object {
        const val USER_KEY = "user"
        const val BOT_KEY = "bot"
    }

    private var messageList: MutableList<MessageModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatBotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var messages = remember {
                        mutableStateListOf<MessageModel>()
                    }
                    messageList = messages
                    ChatScreen(messages = messages) { userMessage ->
                        sendMessage(userMessage = userMessage)
                    }
                }
            }
        }
    }
    private fun sendMessage(userMessage: String) {
        messageList.add(MessageModel(userMessage, USER_KEY))
        // Call the Api
        CoroutineScope(Dispatchers.IO).launch {
            val response = RemoteApiCall.api.getBrainAIMessage(
                bid = 111111,
                key = "1111111",
                uid = USER_KEY,
                msg = userMessage
            )
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.cnt.let {botMessage->
                        messageList.add(MessageModel(botMessage ?: "", BOT_KEY))
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${response.errorBody()?.string()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}


