package com.task.master.domain.repo

import com.task.master.data.model.Tasks

import com.task.master.utils.TaskResult
import com.task.master.utils.resultCallback
import com.task.master.utils.resultListCallback

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


interface HomeRepository {

    suspend fun addTaskCall(
        task: Tasks,
        resultCallback: resultCallback
    )

    suspend fun completeTask(
        task: Tasks,
        resultCallback: resultCallback
    )

    suspend fun getCompletedTasks(
        resultListCallback : resultListCallback
    )
    suspend fun getTasks(
        resultListCallback : resultListCallback
    )

    suspend fun getAllTasks(
        resultListCallback : resultListCallback
    )

}