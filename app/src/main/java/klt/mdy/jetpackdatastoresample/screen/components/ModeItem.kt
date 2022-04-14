package klt.mdy.jetpackdatastoresample.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode


@Composable
fun ModeItem(
    modifier: Modifier = Modifier,
    text: String,
    mode: AppMode,
    selectedMode: AppMode,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = mode == selectedMode,
            onCheckedChange = onCheckedChange,
        )

        Spacer(modifier = modifier.width(16.dp))
        Text(
            text = text,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
@Preview
private fun ModePreview() {
    Surface {
        ModeItem(
            text = "Myanmar",
            mode = AppMode.SYSTEM,
            selectedMode = AppMode.SYSTEM,
            onCheckedChange = {}
        )
    }
}

@Composable
fun LangItem(
    modifier: Modifier = Modifier,
    text: String,
    lang: AppLang,
    selectedLang: AppLang,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 32.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = lang == selectedLang,
            onCheckedChange = onCheckedChange,
        )

        Spacer(modifier = modifier.width(16.dp))
        Text(
            text = text,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
@Preview
private fun LangPreview() {
    Surface {
        LangItem(
            text = "Myanmar",
            lang = AppLang.ENGLISH,
            selectedLang = AppLang.ENGLISH,
            onCheckedChange = {}
        )
    }
}