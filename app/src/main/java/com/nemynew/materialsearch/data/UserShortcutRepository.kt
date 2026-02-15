package com.nemynew.materialsearch.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_shortcuts")

class UserShortcutRepository(private val context: Context) {

    // Key format: "app_package_name" -> "shortcut_word"
    // Since DataStore keys must be static or we iterate, we'll iterate the map.
    // Actually, a better approach for reverse lookup (searching by shortcut) is to store
    // the shortcuts as JSON or a delimited string if we need complex mapping, 
    // but for simple "Label" -> "Component/Package", we can store:
    // Key: "shortcut_word" Value: "package_name/component_name"
    
    // However, user wants to add shortcut TO an app. 
    // Let's store: Key = "shortcut_word", Value = "packageName|componentName"
    
    val shortcutsFlow: Flow<Map<String, String>> = context.dataStore.data
        .map { preferences ->
            preferences.asMap().mapKeys { it.key.name }.mapValues { it.value.toString() }
        }

    suspend fun addShortcut(shortcut: String, packageName: String, componentName: String) {
        val key = stringPreferencesKey(shortcut.lowercase())
        context.dataStore.edit { preferences ->
            preferences[key] = "$packageName|$componentName"
        }
    }

    suspend fun removeShortcut(shortcut: String) {
        val key = stringPreferencesKey(shortcut.lowercase())
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }
}
