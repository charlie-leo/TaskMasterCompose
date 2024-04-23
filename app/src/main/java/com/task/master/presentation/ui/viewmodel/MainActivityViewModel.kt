package com.task.master.presentation.ui.viewmodel

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.master.data.model.Tasks
import com.task.master.domain.usecase.AddTaskUseCase
import com.task.master.domain.usecase.CompleteTaskUseCase
import com.task.master.domain.usecase.GetAllTask
import com.task.master.domain.usecase.GetCompletedTask
import com.task.master.domain.usecase.GetTasks
import com.task.master.presentation.ui.events.HomeUiEvents
import com.task.master.presentation.ui.state.HomeUiState
import com.task.master.utils.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val completeTaskUseCase: CompleteTaskUseCase,
    private val getCompletedTask: GetCompletedTask,
    private val getTasks: GetTasks,
    private val getAllTask: GetAllTask
) : ViewModel() {

    private val TAG = "MainActivityViewModel"

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()


    init {
        viewModelScope.launch (Dispatchers.IO) {
            getTasks()
            getCompletedTasks()
        }

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
    private fun addNewTask(
        task: Tasks
    ) =  viewModelScope.launch (Dispatchers.IO) {
        val list  = _homeUiState.value.taskList

        if (TextUtils.isEmpty(task.taskName)){
            _homeUiState.value = _homeUiState.value.copy(
                showError = true,
                errorMessage = "Task Name is mandatory."
            )
            return@launch
        }
        if (TextUtils.isEmpty(task.taskDetail)){
            _homeUiState.value = _homeUiState.value.copy(
                showError = true,
                errorMessage = "Task Name is mandatory."
            )
            return@launch
        }
        if (TextUtils.isEmpty(task.taskEndDate)){
            _homeUiState.value = _homeUiState.value.copy(
                showError = true,
                errorMessage = "Task Name is mandatory."
            )
            return@launch
        }
        try {
            addTaskUseCase.execute(task){ result ->
                if (result is TaskResult.Success<Tasks>) {
                    _homeUiState.value = _homeUiState.value.copy(
                        showDialog = !_homeUiState.value.showDialog,
                        showError = false
                    )
                    viewModelScope.launch {
                        getCompletedTasks()
                        getTasks()
                    }
                }
            }


        } catch (e: Exception){
            _homeUiState.value = _homeUiState.value.copy(
                showError = true,
                errorMessage = e.message.toString()
            )
        }
    }


    private suspend fun getCompletedTasks(){
        showLoader()
        withContext(Dispatchers.IO){
            getCompletedTask.execute(){result ->
                if (result is TaskResult.Success<List<Tasks>>) {
                    result.data?.let {
                        _homeUiState.value = _homeUiState.value.copy(
                            showError = false,
                            completedTask = it.toMutableList()
                        )
                    }
                } else {
                    result.message?.let{
                        _homeUiState.value = _homeUiState.value.copy(
                            showError = true,
                            errorMessage = it
                        )
                    }
                }
                hideLoader()
            }
        }
    }

    private suspend fun getTasks(){
        showLoader()
        withContext(Dispatchers.IO){
            getTasks.execute(){result ->
                if (result is TaskResult.Success<List<Tasks>>) {
                    result.data?.let {
                        _homeUiState.value = _homeUiState.value.copy(
                            showError = false,
                            taskList = it.toMutableList()
                        )
                    }
                } else {
                    result.message?.let{
                        _homeUiState.value = _homeUiState.value.copy(
                            showError = true,
                            errorMessage = it
                        )
                    }
                }
                hideLoader()
            }

        }
    }

    private suspend fun getAllTasks(){

    }

    private fun showLoader(){
        _homeUiState.value = _homeUiState.value.copy(
            showError = false,
            loading = true
        )
    }
    private fun hideLoader(){
        _homeUiState.value = _homeUiState.value.copy(
            showError = false,
            loading = false
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