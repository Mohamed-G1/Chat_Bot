package com.example.chatbot.uiScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Test() {
    Box(modifier = Modifier.fillMaxSize()) {
        UserMessageCard("message")
    }
}

@Composable
fun UserMessageCard(message: String) {
    Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "User Icon",
            modifier = Modifier
                .size(28.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),

        ) {

            Text(
                text = message,
                color = Color.Black,
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CartItemPerview() {
    Test()
}
