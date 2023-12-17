package com.example.chatbot.uiScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatbot.model.MessageModel


@Composable
fun ChatScreen(messages: List<MessageModel>, onSendMessageClicked: (String) -> Unit) {
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = messages.size) {
        listState.animateScrollToItem(messages.size)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(bottom = 60.dp),
                state = listState
            ) {
                items(messages.size) { index ->
                    when (messages[index].sender) {
                        "user" -> {
                            UserMessageCard(message = messages[index].message)
                        }

                        "bot" -> {
                            BotMessageCard(message = messages[index].message)
                        }
                    }
                }


            }

            // SenderLayout at the bottom
            SenderLayout(modifier = Modifier.align(Alignment.BottomCenter)) { userMessage ->
                onSendMessageClicked(userMessage)
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SenderLayout(modifier: Modifier, onSendMessageClicked: (String) -> Unit) {
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier.weight(5f),
            placeholder = { Text("Enter Message") },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = if (isError) Color.Red else Color.Transparent,
                unfocusedIndicatorColor = if (isError) Color.Red else Color.Transparent,
            ),
            isError = isError
        )

        IconButton(
            onClick = {
                if (message.isEmpty()) {
                    isError = true
                } else {
                    onSendMessageClicked(message)
                    message = ""
                    isError = false
                }

            },
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send Message",
                tint = Color.Black
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ChatItemPreview() {
    ChatScreen(listOf()) {}
}