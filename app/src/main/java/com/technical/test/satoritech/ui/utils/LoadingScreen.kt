package com.technical.test.satoritech.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .semantics { testTag = "loading-screen" },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LoadingPreview() {
    LoadingScreen()
}