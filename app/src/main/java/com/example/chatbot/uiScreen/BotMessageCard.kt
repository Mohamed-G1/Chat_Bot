package com.example.chatbot.uiScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatbot.R
import com.example.chatbot.ui.theme.PurpleGrey80


@Composable
fun BotMessageCard(message: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Card(
            modifier = Modifier.padding(end = 8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = PurpleGrey80),

        ) {
            Text(
                text = message,
                color = Color.Black, // Replace with your color resource
                modifier = Modifier
                    .padding(12.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_android), // Replace with your drawable resource
            contentDescription = "User Icon",
            modifier = Modifier
                .size(28.dp)
        )
    }

}

