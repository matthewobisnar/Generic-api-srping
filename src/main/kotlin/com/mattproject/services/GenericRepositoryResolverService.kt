package com.mattproject.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.mattproject.entities.DocumentFileEntity
import com.mattproject.exceptions.BadRequestException
import com.mattproject.exceptions.NoSuchElementFoundException
import com.mattproject.extension.RepositoryResourceEnum
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericApplicationContext
import org.springframework.data.relational.core.query.Criteria
import org.springframework.stereotype.Service
import java.lang.*
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


/**
 * Example:
 * http://localhost:8082/ph/api/v1/utilities/generics
 * HTTP Verb: GET
 *
 * search={
 *  "repository": "employeeAccountRepository",
 *  "method": "findAll",
 *  "field": [
 *   "employee_code",
 *   "company_code",
 *   "username"
 *  ]
 * }
 *
 */

@Service
class GenericRepositoryResolverService {

    @Autowired
    lateinit var context: GenericApplicationContext

    lateinit var documentFileEntity: DocumentFileEntity;

    @PersistenceContext
    lateinit var entityManager:EntityManager;

    /**
     * @param String of resources payload listed in
     * RepositoryResourceEnum
     * Description:
     *
     * @return
     * @throws NoSuchBeanDefinitionException if no found registered been.
     * @throws ReflectiveOperationException if no method is found.
     * @throws Exception as default exception.
     */
    fun resourceDispatcher(payload: String) : Any? {

        // Get payload
        val mappedPayload = this.isResourceExistWithException(this.mapPayload(payload))

        try {

            // Get bean repository instance
            val instance = context.getBean(mappedPayload.get("repository").toString())
            // Get repository instance method
            val instanceMethod = instance.javaClass.getMethod(mappedPayload.get("method").toString())
            // call method of the instance
            return Optional.of(instanceMethod.invoke(instance)).orElse(null)

        } catch (e:NoSuchBeanDefinitionException) {
            throw BadRequestException("Missing Registered module: " + e.message)
        } catch (e: ReflectiveOperationException) {
            throw BadRequestException("Missing Registered method: cannot find method " +
                    "${mappedPayload.get("method").toString()} in ${mappedPayload.get("repository").toString()}.")
        } catch (e:Exception) {
            throw BadRequestException(e.message)
        }
    }

    /**
     * @param MutableMap of resources payload listed in RepositoryResourceEnum
     * Description:
     *
     * @return Boolean
     */
    private fun isResourceExist(payload: Map<String, Any>) : Boolean {
        var reservedResourceKeys = RepositoryResourceEnum.values().map { it.name.uppercase() }.toList()
        val requiredEnum = (RepositoryResourceEnum.queryFields().map { it.name.uppercase() }.toList())

        var notExists: Boolean = true;

        for (resource in payload) {
            if (!(resource.key.uppercase() in reservedResourceKeys)
                    && (resource.value is String && resource.value.toString().isEmpty())
                        && (resource.key.uppercase() in requiredEnum)) {
                notExists = false
                break;
            }
        }

        return notExists
    }

    /**
     * @param MutableMap of resources payload listed in RepositoryResourceEnum
     * Description:
     *
     * @return MutableMap of resources payload listed in RepositoryResourceEnum
     * @throws NoSuchElementFoundException
     */
    @Throws(RuntimeException::class)
    private fun isResourceExistWithException(payload: Map<String, Any>): Map<String, Any> {

        val reservedResourceKeys = RepositoryResourceEnum.values().map { it.name.uppercase() }.toList()
        val requiredEnum = (RepositoryResourceEnum.required().map { it.name.uppercase() }.toList())

        for (resource in payload) {
            if (!(resource.key.uppercase() in reservedResourceKeys)
                    && (resource.value is String && resource.value.toString().isEmpty())
                        && (resource.key.uppercase() in requiredEnum)) {
                throw NoSuchElementFoundException("Cannot find resource key " + resource.key.uppercase() + " in " + RepositoryResourceEnum::class.simpleName)
            }
        }

        return payload;
    }

    /**
     * @param String of resources payload listed in RepositoryResourceEnum.
     * Description: This converts the string type payload to json object.
     *
     * @return MutableMap if resource payload listed in RepositoryResourceEnum.
     * @throws BadRequestException if payload is empty.
     */
    private fun mapPayload(payload: String) : Map<String, Any> {

        var mapper = ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)

        var payload = mapper.readValue(payload, object : TypeReference<Map<String, Any>>() {})

        if (payload.isEmpty()) {
            throw BadRequestException("Payload must not be empty.")
        }

        return payload
    }

    fun getFunc() {
     var query: Criteria;
    }
}