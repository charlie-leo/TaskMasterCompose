package com.task.master.data.repository.datasourceImpl

import com.task.master.data.local.dao.TaskDao
import com.task.master.data.local.entity.TaskEntity
import com.task.master.data.local.entity.TaskWithFiles
import com.task.master.data.local.repository.TaskRepository
import com.task.master.data.model.Tasks
import com.task.master.data.repository.datasource.HomeDataSource
import com.task.master.utils.TaskResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
class HomeDataSourceImpl(
    private val taskRepository: TaskRepository
)
    : HomeDataSource {
    override suspend fun addTaskCall(task: Tasks): Long {

        return taskRepository.insertTask(task)
    }

    override suspend fun completeTask(task: Tasks): Long {
        return taskRepository.completeTask(task)
    }

    override suspend fun getCompletedTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getCompletedTasksWithFiles()
    }

    override suspend fun getTasks(): Flow<List<TaskWithFiles>> {
        return taskRepository.getTasks()
    }

    override suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getAllTasks()
    }
}