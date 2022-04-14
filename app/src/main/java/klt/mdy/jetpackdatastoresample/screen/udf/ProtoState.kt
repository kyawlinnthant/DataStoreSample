package klt.mdy.jetpackdatastoresample.screen.udf

import klt.mdy.jetpackdatastoresample.model.AppSettings

data class ProtoState(
    val appSettings: AppSettings = AppSettings(),
    val isModeSelected : Boolean = false,
)
