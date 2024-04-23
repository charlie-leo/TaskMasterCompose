package com.task.master.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Charles Raj I on 22/04/24
 * @project TaskMaster Compose
 * @author Charles Raj
 */

class DateUtils private constructor() { // Private constructor to prevent instantiation

    companion object {

        const val DATE_TIME_FORMAT = "dd/MM/yyyy, h:mm a"
        fun formatDate(milliseconds: Long, format: String): String {
            val date = Date(milliseconds)
            val convertedFormat = SimpleDateFormat(format, Locale.getDefault())
            return convertedFormat.format(date)
        }
    }
}