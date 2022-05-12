package com.mattproject.exceptions
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.*


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NoSuchElementFoundException : RuntimeException {
    constructor() : super() {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?) : super(message) {}
    constructor(cause: Throwable?) : super(cause) {}
}