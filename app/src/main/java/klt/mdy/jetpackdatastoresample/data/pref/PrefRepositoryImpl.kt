package klt.mdy.jetpackdatastoresample.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import klt.mdy.jetpackdatastoresample.di.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PrefRepositoryImpl @Inject constructor(
    private val ds: DataStore<Preferences>,
    @Qualifier.Io private val io: CoroutineDispatcher
) : PrefRepository {

    companion object {
        val APP_MODE = intPreferencesKey(name = "com.klt.mode")
        val APP_LANG = intPreferencesKey(name = "com.klt.lang")
        val APP_STATUS = booleanPreferencesKey(name = "com.klt.private")
    }

    override suspend fun putAppMode(mode: Int) {
        withContext(io) {
            ds.edit { it[APP_MODE] = mode }
        }
    }

    override suspend fun pullAppMode(): Flow<Int> {
        return ds.data
            .catch { exception -> if (exception is IOException) emit(emptyPreferences()) else throw  exception }
            .map {
                it[APP_MODE] ?: -1
            }
    }

    override suspend fun putAppLang(lang: Int) {
        withContext(io) {
            ds.edit { it[APP_LANG] = lang }
        }
    }

    override suspend fun pullAppLang(): Flow<Int> {
        return ds.data
            .catch { exception ->
                if (exception is IOException)
                    emit(emptyPreferences())
                else throw  exception
            }
            .map {
                it[APP_LANG] ?: -1
            }
    }

    override suspend fun putPrivateStatus(isPrivate: Boolean) {
        withContext(io) {
            ds.edit { it[APP_STATUS] = isPrivate }
        }
    }

    override suspend fun pullPrivateStatus(): Flow<Boolean> {
        return ds.data
            .catch { exception ->
                if (exception is IOException)
                    emit(emptyPreferences())
                else throw  exception
            }
            .map {
                it[APP_STATUS] ?: false
            }
    }
}
