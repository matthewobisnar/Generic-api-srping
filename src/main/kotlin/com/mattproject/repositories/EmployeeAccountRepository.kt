package com.mattproject.repositories

import com.mattproject.entities.EmployeeAccountEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeAccountRepository : CrudRepository<EmployeeAccountEntity, Int>  {
    override fun findAll(): List<EmployeeAccountEntity>
    fun findById(id: Int?): Optional<EmployeeAccountEntity>
}