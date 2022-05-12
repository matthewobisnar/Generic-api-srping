package com.mattproject.extension

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


class ResponseHandlerUtil {
    companion object {
        fun getResponse(message:String, data: Any?) : ResponseEntity<Any> {

            var map: HashMap<String, Any> = HashMap();
            var status: HttpStatus = HttpStatus.OK;

            if (data == null) {
                status = HttpStatus.BAD_REQUEST
                map.put("message", message)
                map.put("data", arrayOf<Any?>())
            }

            if (data != null) {
                status = HttpStatus.OK
                map.put("data", data)
                map.put("message", status.toString())
            }

            map.put("status", status)
            return ResponseEntity<Any>(map, status)

        }

        fun postResponse() {

        }

        fun putResponse() {

        }
    }
}