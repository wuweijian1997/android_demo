package com.logic.demo.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import com.logic.demo.R
import com.logic.demo.model.CountPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreActivity : AppCompatActivity() {
    private val dataStore: DataStore<Preferences> = baseContext.createDataStore(name = "count")

    private object PreferencesKeys {
        val COUNT = intPreferencesKey("count")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

//        updateCount(10)
    }

    val countPreferencesFlow: Flow<CountPreferences> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val count = preferences[PreferencesKeys.COUNT] ?: 0
        CountPreferences(count)
    }

    suspend fun updateCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.COUNT] = count
        }
    }
}

