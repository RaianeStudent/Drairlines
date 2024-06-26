package com.example.drairlines.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drairlines.R
import com.example.drairlines.components.CampoDeTexto

@Composable
fun TelaHome() {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(28.dp)) {
        Text(text = "Ola raiane")
    }

}

@Preview
@Composable
fun DefaultPreviewOfTelaHome() {
    TelaHome()
}