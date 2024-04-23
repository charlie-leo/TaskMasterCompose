package com.task.master.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.master.data.local.dao.TaskDao
import com.task.master.data.local.entity.FileEntity
import com.task.master.data.local.entity.TaskEntity

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Database(
    entities = [TaskEntity::class, FileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao() : TaskDao

}