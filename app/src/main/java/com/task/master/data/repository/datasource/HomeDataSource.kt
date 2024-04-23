package com.task.master.data.repository.datasource

import com.task.master.data.local.entity.TaskEntity
import com.task.master.data.model.Tasks
import com.task.master.utils.TaskResult
import kotlinx.coroutines.flow.Flow

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
interface HomeDataSource {

    suspend fun addTaskCall(
        task: Tasks
    ) : Long

    suspend fun completeTask(
        task: Tasks
    ) : Long

    suspend fun getCompletedTasks() : Flow<List<TaskEntity>>
    suspend fun getTasks() : Flow<List<TaskEntity>>

    suspend fun getAllTasks() : Flow<List<TaskEntity>>


}