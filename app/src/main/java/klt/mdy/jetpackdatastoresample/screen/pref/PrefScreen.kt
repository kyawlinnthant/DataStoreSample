package klt.mdy.jetpackdatastoresample.screen.pref

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import klt.mdy.jetpackdatastoresample.R
import klt.mdy.jetpackdatastoresample.screen.components.LangBottomSheetContent
import klt.mdy.jetpackdatastoresample.screen.components.ModeBottomSheetContent
import klt.mdy.jetpackdatastoresample.screen.components.SettingsContent
import klt.mdy.jetpackdatastoresample.screen.udf.Actions
import klt.mdy.jetpackdatastoresample.screen.udf.Events
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PrefScreen(
    scaffoldState: ScaffoldState
) {
    val vm: PrefViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val state = vm.state.value
    LaunchedEffect(key1 = true) {
        vm.event.collectLatest {
            when (it) {
                Events.CloseSheet -> {
                    scope.launch {
                        modalBottomSheetState.hide()
                    }
                }
                Events.OpenSheet -> {
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (state.isModeSelected) {
                ModeBottomSheetContent(
                    mode = state.mode,
                    onCheckedChange = {
                        vm.onAction(Actions.OnModeChanged(mode = it))
                    }
                )
            } else {
                LangBottomSheetContent(
                    lang = state.lang,
                    onCheckedChange = {
                        vm.onAction(Actions.OnLangChanged(lang = it))
                    }
                )
            }

        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
        ),
        sheetBackgroundColor = MaterialTheme.colors.surface,

        ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onSurface,
                    elevation = 1.dp,
                    content = {
                        Text(text = stringResource(id = R.string.top_pref))
                    },
                    contentPadding = PaddingValues(
                        horizontal = 16.dp
                    )
                )
            },
            content = {
                SettingsContent(
                    onModeClicked = {
                        vm.onAction(Actions.ClickAppMode)
                    },
                    onLangClicked = {
                        vm.onAction(Actions.ClickAppLang)
                    },
                    onCheckedChanged = {
                        vm.onAction(Actions.OnCheckedChanged(isChecked = it))
                    },
                    mode = state.mode,
                    lang = state.lang,
                    isChecked = state.isChecked,
                )
            }
        )
    }
}