package klt.mdy.jetpackdatastoresample.screen.udf

import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode

sealed class Actions {
    object ClickAppMode : Actions()
    object ClickAppLang : Actions()
    data class OnCheckedChanged(val isChecked: Boolean) : Actions()
    data class OnModeChanged(val mode: AppMode) : Actions()
    data class OnLangChanged(val lang: AppLang) : Actions()

}
