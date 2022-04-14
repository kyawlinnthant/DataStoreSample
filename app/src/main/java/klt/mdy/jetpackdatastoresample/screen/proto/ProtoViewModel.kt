package klt.mdy.jetpackdatastoresample.screen.proto

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import klt.mdy.jetpackdatastoresample.data.proto.ProtoRepository
import klt.mdy.jetpackdatastoresample.model.AppLang
import klt.mdy.jetpackdatastoresample.model.AppMode
import klt.mdy.jetpackdatastoresample.screen.udf.Actions
import klt.mdy.jetpackdatastoresample.screen.udf.Events
import klt.mdy.jetpackdatastoresample.screen.udf.ProtoState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProtoViewModel @Inject constructor(
    private val repo: ProtoRepository
) : ViewModel() {
    private val _state = mutableStateOf(ProtoState())
    val state: State<ProtoState> get() = _state
    private val _event = MutableSharedFlow<Events>()
    val event: SharedFlow<Events> get() = _event

    init {
        getSettings()
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
                updateStatus(action.isChecked)
            }
            is Actions.OnLangChanged -> {
                updateLang(action.lang)
                viewModelScope.launch {
                    _event.emit(
                        Events.CloseSheet
                    )
                }
            }
            is Actions.OnModeChanged -> {
                updateMode(action.mode)
                viewModelScope.launch {
                    _event.emit(
                        Events.CloseSheet
                    )
                }
            }
        }
    }

    private fun updateMode(mode: AppMode) {
        viewModelScope.launch {
            repo.updateAppMode(mode = mode)
        }
    }

    private fun updateLang(lang: AppLang) {
        viewModelScope.launch {
            repo.updateAppLang(lang = lang)
        }
    }

    private fun updateStatus(isChecked: Boolean) {
        viewModelScope.launch {
            repo.updateAppStatus(isPrivate = isChecked)
        }
    }

    private fun getSettings() {
        viewModelScope.launch {
            repo.getAppSettings().collect {
                _state.value = state.value.copy(
                    appSettings = it
                )
            }
        }
    }

}