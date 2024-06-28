package com.example.drairlines.pages


import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.drairlines.components.BotaoComponent
import com.example.drairlines.components.CalendarioViagem
import com.example.drairlines.components.CardBoasVindasCliente
import com.example.drairlines.data.HomeEvent
import com.example.drairlines.data.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHome(homeViewModel: HomeViewModel = viewModel()) {
    Surface {
        Column {
            CardBoasVindasCliente("Ricardo Henrique")
            //Spacer(modifier = Modifier.size(2.dp))
            CalendarioViagem()
            BotaoComponent(value = "Encontre seu voo", onBotaoApertado = {
                homeViewModel.onEvent(HomeEvent.BotãoProcurarVooApertado)
            }, isEnabled = true)
        }
        
        

    }

}

@Preview
@Composable
fun DefaultPreviewOfTelaHome() {
    TelaHome()
}

