package com.task.master.data.repository.repoImpl

import com.task.master.data.local.entity.TaskEntity
import com.task.master.data.local.entity.TaskWithFiles
import com.task.master.data.model.Tasks
import com.task.master.data.repository.datasource.HomeDataSource
import com.task.master.data.repository.datasourceImpl.HomeDataSourceImpl
import com.task.master.domain.repo.HomeRepository
import com.task.master.utils.TaskResult
import com.task.master.utils.resultCallback
import com.task.master.utils.resultListCallback
import java.util.stream.Collectors

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
class HomeRepositoryImpl(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun addTaskCall(
        task: Tasks,
        resultCallback: resultCallback
    ) {
        val result =  homeDataSource.addTaskCall(task)
        if (result > 0) {
            resultCallback(TaskResult.Success(data = task))
        }else {
            resultCallback(TaskResult.Error(message = "Insert not successfully."))
        }
    }

    override suspend fun completeTask(
        task: Tasks,
        resultCallback: resultCallback
    ) {
        val result =  homeDataSource.completeTask(task)
        if (result > 0) {
            resultCallback(TaskResult.Success(data = task))
        }else {
            resultCallback(TaskResult.Error(message = "Insert not successfully."))
        }
    }

    override suspend fun getCompletedTasks(
        resultListCallback: resultListCallback
    ) {
        val result =  homeDataSource.getCompletedTasks()
        result.collect { tasks->
//            resultListCallback(entityToDataList(tasks))
        }
    }

    override suspend fun getTasks(
        resultListCallback: resultListCallback
    ) {
        val result =  homeDataSource.getTasks()
        result.collect { tasks->
            resultListCallback(entityToDataList(tasks))
        }
    }

    override suspend fun getAllTasks(
        resultListCallback: resultListCallback
    ) {
        val result =  homeDataSource.getAllTasks()
        result.collect { tasks->
//            resultListCallback(entityToDataList(tasks))
        }
    }

    private fun entityToDataList(tasks: List<TaskWithFiles>): TaskResult<List<Tasks>> {
        val list: MutableList<Tasks> = mutableListOf()
        tasks.forEach { entity ->
            val taskEntity = entity.taskEntity
            list.add(
                Tasks(
                    id = taskEntity.taskId,
                    taskName = taskEntity.taskName,
                    taskDetail = taskEntity.taskDetail,
                    taskEndDate = taskEntity.taskEndDate,
//                    taskFiles = mutableListOf()
                    taskFiles = entity.taskFiles.stream().map {
                        it.fileUri
                    }.collect(Collectors.toList())
                )
            )
        }
        return TaskResult.Success(data = list)
    }

}