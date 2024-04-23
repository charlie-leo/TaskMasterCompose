package com.task.master.presentation.ui.state

import com.task.master.data.model.Tasks

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


data class HomeUiState(
    val loading: Boolean = false,
    val showDialog: Boolean = false,
    val openTask: Boolean = false,
    val selectedTask : Tasks? = null,
    val taskList: MutableList<Tasks> = mutableListOf(),
    val completedTask: MutableList<Tasks> = mutableListOf(),
    val showError: Boolean = false,
    val errorMessage: String = ""
)

