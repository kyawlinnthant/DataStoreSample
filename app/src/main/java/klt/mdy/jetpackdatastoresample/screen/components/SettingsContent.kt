package klt.mdy.jetpackdatastoresample.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import klt.mdy.jetpackdatastoresample.R
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    onModeClicked: () -> Unit,
    onLangClicked: () -> Unit,
    onCheckedChanged: (Boolean) -> Unit,
    mode: AppMode,
    lang: AppLang,
    isChecked: Boolean,
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SettingsItem(
            title = stringResource(id = R.string.setting_mode),
            chosenField = when (mode) {
                AppMode.LIGHT -> {
                    stringResource(id = R.string.mode_light)
                }
                AppMode.DARK -> {
                    stringResource(id = R.string.mode_dark)
                }
                AppMode.SYSTEM -> {
                    stringResource(id = R.string.mode_system)
                }
                AppMode.DEFAULT -> {
                    stringResource(id = R.string.mode_def)
                }
            },
            onItemClicked = onModeClicked
        )
        Divider()
        SettingsItem(
            title = stringResource(id = R.string.setting_lang),
            chosenField = when (lang) {
                AppLang.ENGLISH -> {
                    stringResource(id = R.string.lang_en)
                }
                AppLang.MYANMAR -> {
                    stringResource(id = R.string.lang_mm)
                }
                AppLang.KOREA -> {
                    stringResource(id = R.string.lang_ko)
                }
                AppLang.DEFAULT -> {
                    stringResource(id = R.string.lang_def)
                }
            },
            onItemClicked = onLangClicked
        )
        Divider()
        SettingsToggleItem(
            title = stringResource(id = R.string.setting_private),
            isSelected = isChecked,
            onCheckedChanged = onCheckedChanged
        )
    }
}