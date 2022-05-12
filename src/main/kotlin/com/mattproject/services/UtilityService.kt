package com.mattproject.services

import com.mattproject.entities.DocumentFileConstructEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.criteria.Path
import javax.persistence.criteria.Root


@Service
class UtilityService {

    @Autowired
    lateinit var entityManager: EntityManager;

    fun actionGetService() : Any {

        val criteriaBuilder = entityManager.criteriaBuilder

        var criteriaQuery = criteriaBuilder.createQuery(DocumentFileConstructEntity::class.java)
        var root: Root<DocumentFileConstructEntity> = criteriaQuery.from(DocumentFileConstructEntity::class.java)
            criteriaQuery.select(root.get("fileName"));

        var typedQuery = entityManager.createQuery(criteriaQuery);

        return (typedQuery.resultStream)
    }


}