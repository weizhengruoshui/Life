package com.yaya.utils

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {

    companion object {
        private const val DEFAULT_PATTERN = "yyyy-MM-dd"

        fun formatDate(date: Date, pattern: String = DEFAULT_PATTERN): String {
            val formatter = SimpleDateFormat(pattern, Locale.getDefault())
            return formatter.format(date)
        }

        fun getCurrentDate(pattern: String): String {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))
        }
    }
}