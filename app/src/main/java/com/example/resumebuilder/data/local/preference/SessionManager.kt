package com.example.resumebuilder.data.local.preference

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val pref: SharedPreferences =
        context.getSharedPreferences("resume_builder_pref", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ONBOARDING = "key_onboarding"
        private const val KEY_LOGIN = "key_login"
    }

    fun setOnboardingCompleted() {
        pref.edit().putBoolean(KEY_ONBOARDING, true).apply()
    }

    fun isOnboardingCompleted(): Boolean {
        return pref.getBoolean(KEY_ONBOARDING, false)
    }

    fun setLoggedIn() {
        pref.edit().putBoolean(KEY_LOGIN, true).apply()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_LOGIN, false)
    }

    fun logout() {
        pref.edit().putBoolean(KEY_LOGIN, false).apply()
    }
}