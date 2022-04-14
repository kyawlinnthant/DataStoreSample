package klt.mdy.jetpackdatastoresample.data.proto

import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode
import klt.mdy.jetpackdatastoresample.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface ProtoRepository {

    suspend fun updateAppMode(mode: AppMode)
    suspend fun updateAppLang(lang: AppLang)
    suspend fun updateAppStatus(isPrivate: Boolean)

    suspend fun getAppSettings() : Flow<AppSettings>

}