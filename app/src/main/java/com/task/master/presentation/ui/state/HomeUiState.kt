package com.task.master.presentation.ui.state

import com.task.master.presentation.ui.screens.Tasks

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
data class HomeUiState(
    val showDialog: Boolean = false,
//    val newTasks: Tasks? = null,
    val taskList: MutableList<Tasks> = mutableListOf(),
    val completedTask: MutableList<Tasks> = mutableListOf()
)
