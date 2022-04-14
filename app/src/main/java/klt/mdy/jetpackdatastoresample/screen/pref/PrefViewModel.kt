package klt.mdy.jetpackdatastoresample.screen.pref

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import klt.mdy.jetpackdatastoresample.data.pref.PrefRepository
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode
import klt.mdy.jetpackdatastoresample.screen.udf.Actions
import klt.mdy.jetpackdatastoresample.screen.udf.Events
import klt.mdy.jetpackdatastoresample.screen.udf.PrefState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrefViewModel @Inject constructor(
    private val repo: PrefRepository
) : ViewModel() {
    private val _state = mutableStateOf(PrefState())
    val state: State<PrefState> get() = _state
    private val _event = MutableSharedFlow<Events>()
    val event: SharedFlow<Events> get() = _event

    init {
        getLang()
        getMode()
        getStatus()
    }

    fun onAction(action: Actions) {
        when (action) {
            Actions.ClickAppLang -> {
                viewModelScope.launch {
                    _event.emit(
                        Events.OpenSheet
                    )
                }
                _state.value = state.value.copy(
                    isModeSelected = false
                )
            }
            Actions.ClickAppMode -> {
                viewModelScope.launch {
                    _event.emit(
                        Events.OpenSheet
                    )
                }
                _state.value = state.value.copy(
                    isModeSelected = true
                )
            }
            is Actions.OnCheckedChanged -> {
                _state.value = state.value.copy(
                    isChecked = action.isChecked
                )
                setStatus(isChecked = action.isChecked)
            }
            is Actions.OnLangChanged -> {
                _state.value = state.value.copy(
                    lang = action.lang
                )
                viewModelScope.launch {
                    _event.emit(
                        Events.CloseSheet
                    )
                }
                setLang(lang = action.lang)
            }
            is Actions.OnModeChanged -> {
                _state.value = state.value.copy(
                    mode = action.mode
                )
                viewModelScope.launch {
                    _event.emit(
                        Events.CloseSheet
                    )
                }
                setMode(mode = action.mode)
            }
        }
    }

    private fun getLang() {
        viewModelScope.launch {
            repo.pullAppLang().collect {
                when (it) {
                    -1 -> {
                        _state.value = state.value.copy(
                            lang = AppLang.DEFAULT
                        )
                    }
                    1 -> {
                        _state.value = state.value.copy(
                            lang = AppLang.MYANMAR
                        )
                    }
                    2 -> {
                        _state.value = state.value.copy(
                            lang = AppLang.ENGLISH
                        )
                    }
                    3 -> {
                        _state.value = state.value.copy(
                            lang = AppLang.KOREA
                        )
                    }
                }
            }
        }
    }

    private fun getMode() {
        viewModelScope.launch {
            repo.pullAppMode().collect {
                when (it) {
                    -1 -> {
                        _state.value = state.value.copy(
                            mode = AppMode.DEFAULT
                        )
                    }
                    1 -> {
                        _state.value = state.value.copy(
                            mode = AppMode.LIGHT
                        )
                    }
                    2 -> {
                        _state.value = state.value.copy(
                            mode = AppMode.DARK
                        )
                    }
                    3 -> {
                        _state.value = state.value.copy(
                            mode = AppMode.SYSTEM
                        )
                    }
                }
            }
        }
    }

    private fun getStatus() {
        viewModelScope.launch {
            repo.pullPrivateStatus().collect {
                _state.value = state.value.copy(
                    isChecked = it
                )
            }
        }
    }

    private fun setMode(mode: AppMode) {
        viewModelScope.launch {
            repo.putAppMode(
                mode = when (mode) {
                    AppMode.DEFAULT -> {
                        -1
                    }
                    AppMode.LIGHT -> {
                        1
                    }
                    AppMode.DARK -> {
                        2
                    }
                    AppMode.SYSTEM -> {
                        3
                    }
                }
            )
        }
    }

    private fun setLang(lang: AppLang) {
        viewModelScope.launch {
            repo.putAppLang(
                lang = when (lang) {
                    AppLang.DEFAULT -> {
                        -1
                    }
                    AppLang.ENGLISH -> {
                        1
                    }
                    AppLang.MYANMAR -> {
                        2
                    }
                    AppLang.KOREA -> {
                        3
                    }
                }
            )
        }
    }

    private fun setStatus(isChecked: Boolean) {
        viewModelScope.launch {
            repo.putPrivateStatus(isPrivate = isChecked)
        }
    }
}