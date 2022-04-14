package klt.mdy.jetpackdatastoresample.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.jetpackdatastoresample.R
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode

@Composable
fun ModeBottomSheetContent(
    modifier: Modifier = Modifier,
    mode: AppMode,
    onCheckedChange: (AppMode) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ModeItem(
            text = stringResource(id = R.string.mode_light),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppMode.LIGHT)
                }
            },
            mode = AppMode.LIGHT,
            selectedMode = mode
        )
        Divider()
        ModeItem(
            text = stringResource(id = R.string.mode_dark),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppMode.DARK)
                }
            },
            mode = AppMode.DARK,
            selectedMode = mode
        )
        Divider()
        ModeItem(
            text = stringResource(id = R.string.mode_system),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppMode.SYSTEM)
                }
            },
            mode = AppMode.SYSTEM,
            selectedMode = mode
        )
    }
}

@Composable
fun LangBottomSheetContent(
    modifier: Modifier = Modifier,
    lang: AppLang,
    onCheckedChange: (AppLang) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LangItem(
            text = stringResource(id = R.string.lang_en),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppLang.ENGLISH)
                }
            },
            lang = AppLang.ENGLISH,
            selectedLang = lang
        )
        Divider()
        LangItem(
            text = stringResource(id = R.string.lang_mm),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppLang.MYANMAR)
                }
            },
            lang = AppLang.MYANMAR,
            selectedLang = lang
        )
        Divider()
        LangItem(
            text = stringResource(id = R.string.lang_ko),
            onCheckedChange = {
                if (it){
                    onCheckedChange(AppLang.KOREA)
                }
            },
            lang = AppLang.KOREA,
            selectedLang = lang
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        LangBottomSheetContent(
            lang = AppLang.MYANMAR,
            onCheckedChange = {}
        )
    }
}