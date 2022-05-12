package com.mattproject.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.mattproject.extension.ResponseHandlerUtil
import com.mattproject.services.EmployeeAccountService
import org.springframework.beans.factory.annotation.Autowired

@RestController
@ControllerAdvice
@RequestMapping("{countryCode}/api/v1/employee")
class EmployeeAccountController {

    @Autowired
    lateinit var employeeAccountService: EmployeeAccountService

    /**
     *
     *
     */
    @RequestMapping(
        value = arrayOf("/fetch", "fetch/{employeeId}"),
        method = arrayOf(RequestMethod.GET)
    )
    fun actionFindAll(
        @PathVariable(name = "employeeId", required = false) employee_id : Int?
    ) : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("something went wrong.", employeeAccountService.fetch(employee_id));
    }

    /**
     *
     *
     */
    @RequestMapping(
        value = arrayOf("fetch-with-role", "fetch-with-role/{employeeId}"),
        method = arrayOf(RequestMethod.GET)
    )
    fun fetchEmployeesWithRole(
        @PathVariable(name = "employeeId", required = false) employeeId : Int?
    ): ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("something went wrong.", null);
    }

}