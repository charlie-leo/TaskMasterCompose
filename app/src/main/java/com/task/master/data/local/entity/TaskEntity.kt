package com.task.master.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Entity(tableName = "TaskTable")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long,

    @ColumnInfo(name = "taskName")
    val taskName: String,

    @ColumnInfo(name = "taskDetail")
    val taskDetail: String,

    @ColumnInfo(name = "taskEndDate")
    val taskEndDate: String,

    @ColumnInfo(name = "taskCompleted")
    val taskCompleted: Int = 0,

//    @Relation(
//        parentColumn = "taskId",
//        entityColumn = "taskId"
//    )
//    val taskFiles: List<FileEntity>

)