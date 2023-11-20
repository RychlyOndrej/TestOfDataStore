import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map





private val Context.dataStore by preferencesDataStore(name = "settings")

class MyController(private val context: Context) {

    suspend fun getValueFromDataStore(): Int {
        val key = intPreferencesKey("example_counter")
        val omg: Flow<Int> = context.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[key] ?: 0
            }
        return omg.first()
    }

    suspend fun saveValueToDataStore(value: Int) {
        val key = intPreferencesKey("example_counter")
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

}
/*
* val key = preferencesKey<Int>("value_key")
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: 0
        }.first()
*
*
*
* val key = preferencesKey<Int>("value_key")
context.dataStore.edit { preferences ->
    preferences[key] = value
}*/