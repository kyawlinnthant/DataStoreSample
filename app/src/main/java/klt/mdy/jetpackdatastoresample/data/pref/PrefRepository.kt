package klt.mdy.jetpackdatastoresample.data.pref

import kotlinx.coroutines.flow.Flow

interface PrefRepository {
    suspend fun putAppMode(mode: Int)
    suspend fun pullAppMode(): Flow<Int>

    suspend fun putAppLang(lang: Int)
    suspend fun pullAppLang(): Flow<Int>

    suspend fun putPrivateStatus(isPrivate: Boolean)
    suspend fun pullPrivateStatus(): Flow<Boolean>
}