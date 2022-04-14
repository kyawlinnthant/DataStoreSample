package klt.mdy.jetpackdatastoresample.data.proto

import androidx.datastore.core.DataStore
import klt.mdy.jetpackdatastoresample.di.Qualifier
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode
import klt.mdy.jetpackdatastoresample.model.AppSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProtoRepositoryImpl @Inject constructor(
    private val ds: DataStore<AppSettings>,
    @Qualifier.Io private val io: CoroutineDispatcher
) : ProtoRepository {
    override suspend fun updateAppMode(mode: AppMode) {
        withContext(io) {
            ds.updateData {
                it.copy(
                    appMode = mode
                )
            }
        }
    }

    override suspend fun updateAppLang(lang: AppLang) {
        withContext(io) {
            ds.updateData {
                it.copy(
                    appLanguage = lang
                )
            }
        }
    }

    override suspend fun updateAppStatus(isPrivate: Boolean) {
        withContext(io) {
            ds.updateData {
                it.copy(
                    appStatus = isPrivate
                )
            }
        }
    }

    override suspend fun getAppSettings(): Flow<AppSettings> {
        return ds.data
    }


}