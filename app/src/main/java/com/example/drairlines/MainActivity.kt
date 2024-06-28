package com.example.drairlines

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.drairlines.app.CiaAereaApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivity.appContext = applicationContext

        setContent {
            CiaAereaApp()
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}

