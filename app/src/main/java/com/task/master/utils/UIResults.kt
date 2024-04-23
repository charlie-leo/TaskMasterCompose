package com.task.master.utils

/**
 * Created by Charles Raj I on 23/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */
sealed class UIResults (type: String? = null, message: String? = null) {

    class Error()

}