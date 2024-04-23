package com.task.master.presentation.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.task.master.presentation.ui.events.HomeUiEvents
import com.task.master.presentation.ui.screens.Tasks
import com.task.master.presentation.ui.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val TAG = "MainActivityViewModel"

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()


    init {

    }

    fun homeUiEvent(event: HomeUiEvents) {
        when (event) {
            HomeUiEvents.ToggleDialog -> {
                toggleDialog()
            }
            is HomeUiEvents.AddTasks -> {
                addNewTask(event.task)
            }
            is HomeUiEvents.CompleteTasks -> {
                addCompletedTask(event.taskIndex)
            }
            is HomeUiEvents.OpenTask -> {
                openTaskDialog(event.task)
            }
            HomeUiEvents.CloseTaskDialog -> {
                closeTaskDialog()
            }
        }
    }

    private fun toggleDialog() {
        _homeUiState.value = _homeUiState.value.copy(
            showDialog = !_homeUiState.value.showDialog
        )
    }
    private fun openTaskDialog(task: Tasks) {
        _homeUiState.value = _homeUiState.value.copy(
            openTask = !_homeUiState.value.openTask,
            selectedTask = task
        )
    }
    private fun closeTaskDialog() {
        _homeUiState.value = _homeUiState.value.copy(
            openTask = !_homeUiState.value.openTask
        )
    }
    private fun addNewTask(task: Tasks) {
        val list  = _homeUiState.value.taskList

        if (TextUtils.isEmpty(task.taskName)){
            return
        }
        if (TextUtils.isEmpty(task.taskDetail)){
            return
        }
        if (TextUtils.isEmpty(task.taskEndDate)){
            return
        }

        list.add(task)

        _homeUiState.value = _homeUiState.value.copy(
            showDialog = !_homeUiState.value.showDialog,
            taskList = list
        )
    }
    private fun addCompletedTask(taskIndex: Int) {
        val taskList  = _homeUiState.value.taskList.toMutableList()
        val completedList  = _homeUiState.value.completedTask.toMutableList()
        if (taskIndex !in 0 until _homeUiState.value.taskList.size){
            return
        }

        val task = taskList[taskIndex]
        taskList.removeAt(taskIndex)
        completedList.add(task)

        _homeUiState.value = _homeUiState.value.copy(
            openTask = false,
            taskList = taskList,
            completedTask = completedList
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

}