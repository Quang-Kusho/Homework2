package com.example.homework1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework1.R
import com.example.homework1.data.Account
import com.example.homework1.data.AccountDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AppViewModel(
    private var db: AccountDao,
    private val notificationService: NotificationService,
): ViewModel() {
    var accountUiState by mutableStateOf(AccountUiState())
        private set

    private val _sensorValuesUiState = MutableStateFlow(SensorValuesUIState())
    val sensorValuesUIState: StateFlow<SensorValuesUIState> = _sensorValuesUiState.asStateFlow()

    var accountUiStates: StateFlow<AccountUiStates> =
        db.getAllProfiles().map { AccountUiStates(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = AccountUiStates()
            )

    fun resetAccountUiState() {
        accountUiState = AccountUiState()
    }

    fun updateAccountUiState(account: Account) {
        accountUiState = AccountUiState(
            account = account,
            isValidateInput = validateInput(account)
        )
    }

    suspend fun saveAccount() {
        if (validateInput()) {
            db.insert(accountUiState.account)
        }
    }

    suspend fun updateAccount() {
        if (validateInput()) {
            db.update(accountUiState.account)
        }
    }

    suspend fun deleteAccount() {
        db.delete(accountUiState.account)
    }

    private fun validateInput(account: Account = accountUiState.account): Boolean {
        return with(account) {
            name.isNotBlank() && info.isNotBlank()
        }
    }

    private fun showNotification(
        id: Int,
        channelID: String,
        launcherForegroundID: Int,
        title: String,
        text: String,
        priority: Int,
        bigText: String = ""
    ) {
        notificationService.showNotification(
            id = id,
            channelID = channelID,
            launcherForegroundID = launcherForegroundID,
            title = title,
            text = text,
            priority = priority,
            bigText = bigText
        )
    }

    fun updateLightSensorValue(lux: Float = 0f) {
        val notificationID = 100
        val notificationChannelID = "notification_theme"
        val launcherForegroundID = R.drawable.ava
        val title = "light is changing"
        val priority = PRIORITY_HIGH

        _sensorValuesUiState.update { currentState ->
            currentState.copy(
                x = lux
            )
        }
        if(notificationService.notiManager.areNotificationsEnabled()) {
            showNotification(
                id = notificationID,
                channelID = notificationChannelID,
                launcherForegroundID = launcherForegroundID,
                title = title,
                text = "lux value is $lux",
                priority = priority
            )
        }

    }
}

data class AccountUiState(
    val account: Account = Account(
        name = "",
        info = ""
    ),
    val isValidateInput: Boolean = false
)

data class AccountUiStates(
    val accountList: List<Account> = listOf()
)

data class SensorValuesUIState(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f
)
