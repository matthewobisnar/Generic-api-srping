package com.mattproject.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import kotlin.system.exitProcess


@Service
class CQueryBuilder  {

    @Autowired
    @PersistenceContext
    private val entityManager: EntityManager? = null

    fun query() {
        val builder: CriteriaBuilder = entityManager!!.criteriaBuilder
        val criteria: CriteriaQuery<Long> = builder.createQuery(Long::class.java)

        println(criteria)
        exitProcess(1)
    }
}