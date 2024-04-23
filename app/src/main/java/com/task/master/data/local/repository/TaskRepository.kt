package com.task.master.data.local.repository

import com.task.master.data.local.dao.TaskDao
import com.task.master.data.local.entity.FileEntity
import com.task.master.data.local.entity.TaskEntity
import com.task.master.data.local.entity.TaskWithFiles
import com.task.master.data.model.Tasks
import kotlinx.coroutines.flow.Flow
import java.util.Collections

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
class TaskRepository (
    private val taskDao : TaskDao
) {
    fun insertTask(task: Tasks): Long {
        val fileEntities : MutableList<FileEntity> = mutableListOf()
        task.taskFiles.forEach {
            fileEntities.add(
                FileEntity(fileUri = it)
            )
        }
        return taskDao.insertTaskWithFile( tasks = TaskEntity(
            taskId = task.id,
            taskName = task.taskName,
            taskDetail = task.taskDetail,
            taskEndDate = task.taskEndDate
        ), fileEntity = fileEntities.toList())
    }

    fun completeTask(task: Tasks): Long {
        return taskDao.completeTask(TaskEntity(
            taskId = task.id,
            taskName = task.taskName,
            taskDetail = task.taskDetail,
            taskEndDate = task.taskEndDate
        )).toLong()
    }

    fun getCompletedTasksWithFiles(): Flow<List<TaskEntity>> {
        return taskDao.getCompletedTasksWithFiles()
    }

    fun getTasks(): Flow<List<TaskWithFiles>> {
        return taskDao.getTasksWithFiles()
    }

    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasksWithFiles()
    }


}