package com.example.drairlines.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drairlines.MainActivity
import com.example.drairlines.R
import com.example.drairlines.data.TrechoDTOEstado
import com.example.drairlines.network.SessionManager
import com.example.drairlines.ui.theme.CorTexto
import com.example.drairlines.ui.theme.Purple80
import com.example.drairlines.ui.theme.Roxinho
import com.example.drairlines.ui.theme.componentShapes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun MyCard(data: TrechoDTOEstado,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .heightIn(100.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Purple80
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Text(text = data.nome)
        Text(text = data.origem)
        Text(text = data.destino)
        Text(text = data.horaChegada)
        Text(text = data.horaPartida)
        Text(text = data.valor.toString())
        Text(text = data.nome)
    }
}

@Composable
fun CardBoasVindasCliente(value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .heightIn(100.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Purple80
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        content = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Olá, $value",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Qual será a sua próxima viagem?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal)
                }
            }

        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioViagem() {
    lateinit var sessionManager: SessionManager
    sessionManager = SessionManager(MainActivity.appContext)
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog = remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    var selectedDate = remember {
        mutableStateOf("")
    }

    if (showDatePickerDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog.value = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate.value = millis.toBrazilianDateFormat()
                                sessionManager.saveData(selectedDate.value)
                            }

                        showDatePickerDialog.value = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    TextField(
        value = selectedDate.value,
        onValueChange = {  },
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog.value = true
                    focusManager.clearFocus(force = true)
                }
            }
        ,
        colors = TextFieldDefaults.textFieldColors(Purple80),
        label = {
            Text("Escolha uma data")
        },
        readOnly = true
    )
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

@Composable
fun HeadingTextoComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = CorTexto,
        textAlign = TextAlign.Center
    )
}

@Composable
fun NormalTextoComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = CorTexto,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoDeTexto(valorLabel: String, onTextSelect: (String) -> Unit, errorStatus: Boolean = false) {

    val valorTexto = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .clip(componentShapes.small)
            .heightIn(),
        label = { Text(text = valorLabel) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = valorTexto.value,
        onValueChange = {
            valorTexto.value = it
            onTextSelect(it)
        },
        isError = !errorStatus
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SenhaCampoDeTexto(
    valorLabel: String,
    visual: VisualTransformation,
    onTextSelect: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val localFocusManager = LocalFocusManager.current

    val valorTexto = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .clip(componentShapes.small)
            .heightIn(),
        label = { Text(text = valorLabel) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = valorTexto.value,
        onValueChange = {
            valorTexto.value = it
            onTextSelect(it)
        },
        visualTransformation = visual,
        isError = !errorStatus
    )


}

@Composable
fun BotaoComponent(value: String, onBotaoApertado: () -> Unit, isEnabled: Boolean = false) {
    Button(
        onClick = {
            onBotaoApertado.invoke()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Roxinho
        ),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TextoClicavel(value: String, textoSelecionado: (String) -> Unit) {
    val textoInicio = "Ainda não tem uma conta? "
    val facaCadastro = "Cadastre-se"

    val fraseClique = buildAnnotatedString {
        append(textoInicio)
        withStyle(style = SpanStyle(color = Roxinho)) {
            pushStringAnnotation(tag = facaCadastro, annotation = facaCadastro)
            append(facaCadastro)

        }
    }
    ClickableText(text = fraseClique, onClick = { offset ->

        fraseClique.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                android.util.Log.d("ClickableComponent", "{${span.item}}")

                if (span.item == facaCadastro) {
                    textoSelecionado(span.item)
                }
            }

    })

}

@Composable
fun TextoCadastroClicavel(value: String, textoSelecionado: (String) -> Unit) {
    val textoInicio = "Já tem uma conta? "
    val realizeLogin = "Login"

    val fraseClique = buildAnnotatedString {
        append(textoInicio)
        withStyle(style = SpanStyle(color = Roxinho)) {
            pushStringAnnotation(tag = realizeLogin, annotation = realizeLogin)
            append(realizeLogin)

        }
    }
    ClickableText(text = fraseClique, onClick = { offset ->

        fraseClique.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                android.util.Log.d("ClickableComponent", "{${span.item}}")

                if (span.item == realizeLogin) {
                    textoSelecionado(span.item)
                }
            }

    })

}

