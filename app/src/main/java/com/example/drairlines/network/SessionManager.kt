package com.example.drairlines.network

import android.content.Context
import android.content.SharedPreferences
import com.example.drairlines.R
import com.example.drairlines.data.LoginViewModel
import com.example.drairlines.data.TrechoDTOEstado
import com.example.drairlines.navigation.Tela

class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "token"
        const val DATA_SELECT = "data"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun saveData(data: String) {
        val editor = prefs.edit()
        editor.putString(DATA_SELECT, data)
        editor.apply()
    }

    fun getData() : String ? {
        return prefs.getString(DATA_SELECT, null)
    }

    fun revokeAuthToken() {
        val editor = prefs.edit()
        editor.remove(USER_TOKEN)
        editor.apply()
    }

}