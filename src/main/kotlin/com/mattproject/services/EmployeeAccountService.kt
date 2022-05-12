package com.mattproject.services

import com.mattproject.repositories.EmployeeAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager

@Service
class EmployeeAccountService {

    @Autowired
    lateinit var employeeAccountRepository: EmployeeAccountRepository;

    @Autowired
    @PersistenceContext
    lateinit var entityManager: EntityManager;

    /**
     *
     *
     */
    fun fetch(employeeId:Int?) : Any? {

        if (employeeId == null) {
            return employeeAccountRepository.findAll();
        }
        return employeeAccountRepository.findById(employeeId).orElse(null)
    }


}