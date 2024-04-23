package com.task.master.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.task.master.data.local.entity.TaskEntity
import com.task.master.data.model.Tasks
import kotlinx.coroutines.flow.Flow

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Dao
interface TaskDao {

    @Query("Select * From TaskTable")
    fun getAllTasksWithFiles() : Flow<List<TaskEntity>>

    @Query("Select * From TaskTable where taskCompleted = 1")
    fun getCompletedTasksWithFiles() : Flow<List<TaskEntity>>

    @Query("Select * From TaskTable where taskCompleted = 0")
    fun getTasksWithFiles() : Flow<List<TaskEntity>>

    @Insert
    fun insertTask(tasks: TaskEntity) : Long

    //    @Query("Upda")
    @Update
    fun completeTask(tasks: TaskEntity) : Int

}