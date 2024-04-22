package com.task.master.presentation.ui.events

import com.task.master.presentation.ui.screens.Tasks

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

sealed interface HomeUiEvents {
    data object ToggleDialog : HomeUiEvents
    data class AddTasks(val task: Tasks): HomeUiEvents
    data class CompleteTasks(val taskIndex: Int) : HomeUiEvents
}