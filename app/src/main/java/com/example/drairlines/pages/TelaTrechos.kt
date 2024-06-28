package com.example.drairlines.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drairlines.components.MyCard

import com.example.drairlines.data.TrechoViewModel

@Composable
fun TelaTrechos(trechoViewModel: TrechoViewModel = viewModel()) {


    Surface {
        LazyColumn(

        ) {

        }

    }
}

@Preview
@Composable
fun DefaultPreviewOfTelaTrecho(){
    TelaTrechos()
}



