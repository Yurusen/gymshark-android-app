package android.angel.gymshark.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("datastore")

@Singleton
class DataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun login(
        isLoggedIn: Boolean,
        email: String
    ) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = isLoggedIn
            prefs[EMAIL] = email
        }
    }

    val isLoggedIn: Flow<Boolean?> = context.dataStore.data.map { it[IS_LOGGED_IN] }
    val email: Flow<String?> = context.dataStore.data.map { it[EMAIL] }

}