package com.logic.demo.logic.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.createDataStore
import com.logic.demo.MainApplication
import com.logic.demo.model.CountPreferences
import com.logic.demo.ui.page.DataStoreActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

object CountDataStore {
    private val dataStore: DataStore<Preferences> =
        MainApplication.context.createDataStore(name = "count")

    private val countPreferencesFlow: Flow<CountPreferences> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val count = preferences[PreferencesKeys.COUNT] ?: 0
        Log.d("Count", "$count")
        CountPreferences(count)
    }

    private object PreferencesKeys {
        val COUNT = intPreferencesKey("count")
    }

    suspend fun get(): CountPreferences {
        return countPreferencesFlow.first()
    }

    suspend fun update(count: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.COUNT] = count
        }
    }
}