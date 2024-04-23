package com.task.master.data.model

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
data class Tasks(
    var id: Long,
    var taskName: String,
    var taskDetail: String,
    var taskEndDate: String,
    var taskFiles: MutableList<String>
)

