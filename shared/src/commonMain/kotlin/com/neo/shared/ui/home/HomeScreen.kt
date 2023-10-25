package com.neo.shared.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) = Column(
    modifier,
    Arrangement.Center,
    Alignment.CenterHorizontally
) {

    Text(
        text = "JSON Tree Viewer",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
    )

    Spacer(Modifier.height(16.dp))

    Button(onClick = { /*TODO*/ }) {
        Text("select file")
    }
}
