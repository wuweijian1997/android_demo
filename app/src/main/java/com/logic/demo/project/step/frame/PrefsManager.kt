package com.logic.demo.project.step.frame

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(val context: Context) {
    private val preferencesName = "STEP"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }


    fun contains(): Boolean {
        return sharedPreferences.contains(preferencesName)
    }


    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getBooleanDefaultTrue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, true)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0.0f)
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun putBoolean(key: String, value: Boolean): Boolean {
        return sharedPreferences.edit().putBoolean(key, value)
            .commit()
    }

    fun putFloat(key: String, value: Float): Boolean {
        return sharedPreferences.edit().putFloat(key, value).commit()
    }

    fun putInt(key: String, value: Int): Boolean {
        return sharedPreferences.edit().putInt(key, value).commit()
    }

    fun putLong(key: String, value: Long): Boolean {
        return sharedPreferences.edit().putLong(key, value).commit()
    }

    fun putString(key: String, value: String): Boolean {
        return sharedPreferences.edit().putString(key, value).commit()
    }
}