package com.mattproject.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GlobalUtil {
    val SYSTEM_GENERATED: String = "00-00000"

    fun currentDate(): String? {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        return current.format(formatter)
    }
}