package com.mattproject.repositories

import com.mattproject.entities.DocumentFileEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DocumentFileRepository : CrudRepository<DocumentFileEntity, Int> {
    override fun findAll(): List<DocumentFileEntity>
//    fun findAllFileName()
}