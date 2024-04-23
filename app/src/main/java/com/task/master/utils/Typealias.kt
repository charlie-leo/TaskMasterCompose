package com.task.master.utils

import com.task.master.data.model.Tasks

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */


typealias resultCallback = (result : TaskResult<Tasks>) -> Unit
typealias resultListCallback = (result : TaskResult<List<Tasks>>) -> Unit
