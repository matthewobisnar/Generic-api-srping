package com.mattproject.services

import com.mattproject.repositories.EmployeeAccountRoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeAccountRoleService {

    @Autowired
    lateinit var employeeAccountRoleRepository: EmployeeAccountRoleRepository;

    fun fetch(roleId:Int?) : Any? {

        if (roleId == null) {
            var employeeList = employeeAccountRoleRepository.findAll();

            if (!employeeList.isNullOrEmpty()) {
                return employeeList;
            }
        }

        return employeeAccountRoleRepository.findById(roleId).orElse(null)
    }
}