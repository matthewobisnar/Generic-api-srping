package com.mattproject.controllers

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import com.mattproject.services.EmployeeAccountRoleService
import com.mattproject.extension.ResponseHandlerUtil
import org.springframework.http.ResponseEntity

@RestController
@ControllerAdvice
@RequestMapping("{countryCode}/api/v1/employee-role")
class EmployeeAccountRoleController {

    @Autowired
    lateinit var roleService: EmployeeAccountRoleService;

    @RequestMapping(
        value = arrayOf("/fetch", "fetch/{roleId}"),
        method = arrayOf(RequestMethod.GET)
    )
    fun actionFindAll(
        @PathVariable(name = "roleId", required = false) roleId : Int?
    ) : ResponseEntity<Any> {
        return ResponseHandlerUtil.getResponse("something went wrong.", roleService.fetch(roleId));
    }

}