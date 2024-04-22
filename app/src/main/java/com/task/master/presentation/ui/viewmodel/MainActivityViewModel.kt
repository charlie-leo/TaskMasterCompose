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

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()


    init {

//        val taskList =
//            mutableListOf<Tasks>(
//                Tasks(id = 1, "Need To put the trash out by today","","", mutableListOf()),
//                Tasks(id = 2, "Need To put clean the house.","","", mutableListOf()),
//                Tasks(id = 3, "Go for shopping with friends","","", mutableListOf()),
//                Tasks(id = 4, "complete the frozen food now","","", mutableListOf()),
//                Tasks(id = 5, "Take care of the kitty,","","", mutableListOf()),
//            )
//        _homeUiState.value.taskList.addAll(taskList)
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
        }
    }

    private fun toggleDialog() {
        _homeUiState.value = HomeUiState(
            !_homeUiState.value.showDialog,
            _homeUiState.value.taskList,
            _homeUiState.value.completedTask)
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

        _homeUiState.value = HomeUiState(
            !_homeUiState.value.showDialog,
            list,
            _homeUiState.value.completedTask)
    }
    private fun addCompletedTask(taskIndex: Int) {
        val taskList  = _homeUiState.value.taskList
        val completedList  = _homeUiState.value.completedTask

        val task = taskList[taskIndex]
        taskList.removeAt(taskIndex)
        completedList.add(task)

        _homeUiState.value = HomeUiState(
            _homeUiState.value.showDialog,
            taskList,
            completedList
        )
    }

    override fun onCleared() {
        super.onCleared()
    }

}