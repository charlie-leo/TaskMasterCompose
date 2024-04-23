package com.task.master.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
data class TaskWithFiles(
    @Embedded val taskEntity: TaskEntity,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskId"
    )
    val taskFiles: List<FileEntity>
)

