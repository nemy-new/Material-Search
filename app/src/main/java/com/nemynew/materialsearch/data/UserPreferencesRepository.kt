package com.nemynew.materialsearch.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesRepository(private val context: Context) {

    companion object {
        val KEY_PINNED_APPS = stringSetPreferencesKey("pinned_apps")
        val KEY_HIDDEN_APPS = stringSetPreferencesKey("hidden_apps")
    }

    val pinnedApps: Flow<Set<String>> = context.userPreferencesDataStore.data
        .map { preferences ->
            preferences[KEY_PINNED_APPS] ?: emptySet()
        }

    val hiddenApps: Flow<Set<String>> = context.userPreferencesDataStore.data
        .map { preferences ->
            preferences[KEY_HIDDEN_APPS] ?: emptySet()
        }

    suspend fun pinApp(packageName: String, componentName: String) {
        val key = "$packageName/$componentName"
        context.userPreferencesDataStore.edit { preferences ->
            val currentPinned = preferences[KEY_PINNED_APPS] ?: emptySet()
            preferences[KEY_PINNED_APPS] = currentPinned + key
        }
    }

    suspend fun unpinApp(packageName: String, componentName: String) {
        val key = "$packageName/$componentName"
        context.userPreferencesDataStore.edit { preferences ->
            val currentPinned = preferences[KEY_PINNED_APPS] ?: emptySet()
            preferences[KEY_PINNED_APPS] = currentPinned - key
        }
    }

    suspend fun hideApp(packageName: String, componentName: String) {
        val key = "$packageName/$componentName"
        context.userPreferencesDataStore.edit { preferences ->
            val currentHidden = preferences[KEY_HIDDEN_APPS] ?: emptySet()
            // If hiding, also unpin just in case
            val currentPinned = preferences[KEY_PINNED_APPS] ?: emptySet()
            if (currentPinned.contains(key)) {
                preferences[KEY_PINNED_APPS] = currentPinned - key
            }
            preferences[KEY_HIDDEN_APPS] = currentHidden + key
        }
    }

    suspend fun unhideApp(key: String) {
        context.userPreferencesDataStore.edit { preferences ->
            val currentHidden = preferences[KEY_HIDDEN_APPS] ?: emptySet()
            preferences[KEY_HIDDEN_APPS] = currentHidden - key
        }
    }
}
