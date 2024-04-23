package com.task.master.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

@Entity(tableName = "FileTable",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["taskId"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
    )
data class FileEntity (
    @PrimaryKey (autoGenerate = true)
    val fileId : Long = 0,
    var taskId : Long = 0,
    val fileUri: String
)