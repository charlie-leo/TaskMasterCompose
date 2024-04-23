package com.task.master.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.task.master.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Dao
interface TaskDao {

    @Query("Select * From tasktable ")
    fun getAllTasksWithFiles() : Flow<List<TaskEntity>>

}