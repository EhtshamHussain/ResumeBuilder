package com.example.resumebuilder.data.local.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("resume_builder_pref", Context.MODE_PRIVATE)
    companion object {
        private const val KEY_ONBOARDING = "key_onboarding"
        private const val KEY_LOGIN = "key_login"
        private const val KEY_USER_NAME = "key_user_name"
        private const val KEY_USER_EMAIL = "key_user_email"
    }
    fun setOnboardingCompleted() {
        pref.edit { putBoolean(KEY_ONBOARDING, true) }
    }
    fun isOnboardingCompleted(): Boolean {
        return pref.getBoolean(KEY_ONBOARDING, false)
    }
    fun setLoggedIn(name: String = "", email: String = "") {
        pref.edit {
            putBoolean(KEY_LOGIN, true)
                .putString(KEY_USER_NAME, name)
                .putString(KEY_USER_EMAIL, email)
        }
    }
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_LOGIN, false)
    }
    fun getUserName(): String? {
        return pref.getString(KEY_USER_NAME, null)
    }
    fun getUserEmail(): String? {
        return pref.getString(KEY_USER_EMAIL, null)
    }
    fun logout() {
        pref.edit {
            putBoolean(KEY_LOGIN, false)
                .remove(KEY_USER_NAME)
                .remove(KEY_USER_EMAIL)
        }
    }
}