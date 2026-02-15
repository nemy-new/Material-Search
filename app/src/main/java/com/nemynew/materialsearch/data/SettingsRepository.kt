package com.nemynew.materialsearch.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    companion object {
        val KEY_IS_SEARCH_BAR_BOTTOM = booleanPreferencesKey("is_search_bar_bottom")
        val KEY_AUTO_SHOW_KEYBOARD = booleanPreferencesKey("auto_show_keyboard")
        val KEY_GRID_COLUMN_COUNT = androidx.datastore.preferences.core.intPreferencesKey("grid_column_count")
        val KEY_BLUR_RADIUS = androidx.datastore.preferences.core.intPreferencesKey("blur_radius")
        val KEY_BACKGROUND_ALPHA = androidx.datastore.preferences.core.floatPreferencesKey("background_alpha")
        val KEY_ICON_PACK_PACKAGE = androidx.datastore.preferences.core.stringPreferencesKey("icon_pack_package")
        val KEY_ICON_SIZE = androidx.datastore.preferences.core.floatPreferencesKey("icon_size")
        val KEY_SHOW_ICON_LABELS = booleanPreferencesKey("show_icon_labels")
        val KEY_IS_FIRST_RUN = booleanPreferencesKey("is_first_run")
    }

    val isSearchBarBottom: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_IS_SEARCH_BAR_BOTTOM] ?: false // Default to Top (false)
        }

    val autoShowKeyboard: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_AUTO_SHOW_KEYBOARD] ?: true // Default to True
        }

    val gridColumnCount: Flow<Int> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_GRID_COLUMN_COUNT] ?: 4 // Default to 4
        }

    val blurRadius: Flow<Int> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_BLUR_RADIUS] ?: 60 // Default to 60
        }

    val backgroundAlpha: Flow<Float> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_BACKGROUND_ALPHA] ?: 0.5f // Default to 0.5
        }

    val iconPackPackage: Flow<String> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_ICON_PACK_PACKAGE] ?: "" // Default to empty (System icons)
        }

    val iconSize: Flow<Float> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_ICON_SIZE] ?: 1.0f // Default to 1.0 (Standard)
        }

    suspend fun setSearchBarPosition(isBottom: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_IS_SEARCH_BAR_BOTTOM] = isBottom
        }
    }

    suspend fun setAutoShowKeyboard(autoShow: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_AUTO_SHOW_KEYBOARD] = autoShow
        }
    }

    suspend fun setGridColumnCount(count: Int) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_GRID_COLUMN_COUNT] = count
        }
    }

    suspend fun setBlurRadius(radius: Int) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_BLUR_RADIUS] = radius
        }
    }

    suspend fun setBackgroundAlpha(alpha: Float) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_BACKGROUND_ALPHA] = alpha
        }
    }

    suspend fun setIconPackPackage(packageName: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_ICON_PACK_PACKAGE] = packageName
        }
    }

    suspend fun setIconSize(size: Float) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_ICON_SIZE] = size
        }
    }

    val showIconLabels: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_SHOW_ICON_LABELS] ?: true // Default to true
        }

    suspend fun setShowIconLabels(show: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_SHOW_ICON_LABELS] = show
        }
    }

    val isFirstRun: Flow<Boolean> = context.settingsDataStore.data
        .map { preferences ->
            preferences[KEY_IS_FIRST_RUN] ?: true // Default to True
        }

    suspend fun setFirstRunCompleted() {
        context.settingsDataStore.edit { preferences ->
            preferences[KEY_IS_FIRST_RUN] = false
        }
    }
}
