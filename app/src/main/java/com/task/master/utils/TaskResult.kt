package com.task.master.utils


/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
sealed class TaskResult<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : TaskResult<T>(data)
    class Loading<T>(data: T? = null) : TaskResult<T>(data)
    class Error<T>(data: T? = null, message: String?) : TaskResult<T>(data, message)
}