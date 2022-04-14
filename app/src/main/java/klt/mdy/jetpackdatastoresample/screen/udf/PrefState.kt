package klt.mdy.jetpackdatastoresample.screen.udf

import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode

data class PrefState(
    val isChecked :Boolean = false,
    val mode : AppMode = AppMode.DEFAULT,
    val lang : AppLang = AppLang.DEFAULT,
    val isModeSelected : Boolean = false,
)
