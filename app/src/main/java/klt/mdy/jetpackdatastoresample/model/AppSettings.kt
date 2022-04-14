package klt.mdy.jetpackdatastoresample.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val appMode: AppMode = AppMode.DEFAULT,
    val appLanguage: AppLang = AppLang.DEFAULT,
    val appStatus: Boolean = false,
)
