package klt.mdy.jetpackdatastoresample.model

import kotlinx.serialization.Serializable

enum class AppMode {
    LIGHT, DARK, SYSTEM,DEFAULT
}

@Serializable
enum class AppLang {
    MYANMAR, ENGLISH, KOREA,DEFAULT
}