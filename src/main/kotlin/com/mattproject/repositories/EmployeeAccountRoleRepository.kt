package com.mattproject.repositories

import com.mattproject.entities.EmployeeAccountRoleEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeAccountRoleRepository: CrudRepository<EmployeeAccountRoleEntity, Int> {
    override fun findAll(): List<EmployeeAccountRoleEntity>
    fun findById(id: Int?): Optional<EmployeeAccountRoleEntity>
}