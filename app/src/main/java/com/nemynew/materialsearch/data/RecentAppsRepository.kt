package com.nemynew.materialsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val android.content.Context.recentAppsDataStore by androidx.datastore.preferences.preferencesDataStore(name = "recent_apps")

class RecentAppsRepository(private val context: Context) {

    companion object {
        val KEY_RECENT_APPS = stringPreferencesKey("recent_apps_list")
        const val MAX_RECENT_APPS = 5
    }

    // Stored as a delimited string: "pkg1/cls1|pkg2/cls2|..."
    val recentApps: Flow<List<String>> = context.recentAppsDataStore.data
        .map { preferences ->
            val raw = preferences[KEY_RECENT_APPS] ?: ""
            if (raw.isBlank()) emptyList() else raw.split("|")
        }

    suspend fun addRecentApp(componentName: String) {
        context.recentAppsDataStore.edit { preferences ->
            val currentRaw = preferences[KEY_RECENT_APPS] ?: ""
            val currentList = if (currentRaw.isBlank()) mutableListOf() else currentRaw.split("|").toMutableList()

            // Remove if already exists (to move to front)
            currentList.remove(componentName)
            
            // Add to front
            currentList.add(0, componentName)

            // Keep only last N
            if (currentList.size > MAX_RECENT_APPS) {
                currentList.subList(MAX_RECENT_APPS, currentList.size).clear()
            }

            preferences[KEY_RECENT_APPS] = currentList.joinToString("|")
        }
    }
}
