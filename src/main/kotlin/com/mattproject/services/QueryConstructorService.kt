package com.mattproject.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.mattproject.exceptions.BadRequestException
import com.mattproject.exceptions.NoSuchElementFoundException
import com.mattproject.extension.RepositoryResourceEnum
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Path
import javax.persistence.criteria.Root
import java.lang.*
import java.util.*

@Service
class QueryConstructorService {

    @Autowired
    lateinit var entityManager: EntityManager;

    protected lateinit var criteriaQuery: CriteriaQuery<*>

    protected lateinit var entityClass: Class<*>;

    protected lateinit var typeQuery: TypedQuery<*>

    protected lateinit var elements: Root<*>;

    protected var entityTablePrefix: String? = null

    /**
     * @param String
     * Description: create Criteria instance and call methods from this instance.
     *
     * @return this instance
     */
    fun queryInit(query: String) : QueryConstructorService {

        val mappedPayload = this.isResourceExistWithException(this.mapPayload(query))
        var reservedResourceKeys = RepositoryResourceEnum.queryFields().map { it.name.uppercase() }.toList()

        this.criteriaQuery = entityManager.criteriaBuilder.createQuery()
        this.entityClass = this.getEntityClass(entityManager, mappedPayload.get("repository").toString().capitalize())!!
        this.elements = criteriaQuery.from(this.entityClass)
        this.entityTablePrefix = this.getTablePrefix(this.entityClass);

        for (payload in mappedPayload) {
            if (reservedResourceKeys.contains(payload.key.uppercase())) {
                try {

                    val instanceMethod = this.javaClass.getMethod(this.refactorField(payload.key) + "Resolver", String::class.java)
                    Optional.of(instanceMethod.invoke(this, query)).orElse(null)
                    return this

                } catch (e:NoSuchBeanDefinitionException) {
                    throw BadRequestException("Missing Registered module: " + e.message)
                } catch (e: ReflectiveOperationException) {
                    throw BadRequestException("Missing Registered method: cannot find method " +
                            "${mappedPayload.get("method").toString()} in ${mappedPayload.get("repository").toString()}.")
                } catch (e:Exception) {
                    throw BadRequestException(e.message)
                }

            }
        }

        return this
    }

    /**
     * @oaram Entity class
     * Description: Get entity class table prefix.
     *
     * @Return String table_prefix.
     */
    private fun getTablePrefix(param: Class<*>) : String {
        return param.getMethod("tablePrefix").invoke(param.newInstance()).toString()
    }

    /**
     * @param EntityManager
     * @param String from payload
     * Description: Get Entity class from repository key payload.
     *
     * Note: This function can be optimized - 2022-05-12
     *
     * @return Entity class
     */
    fun getEntityClass(entityManager: EntityManager, entityName: String): Class<*>? {

        for (entity in entityManager.metamodel.entities) {
            if (entityName == entity.name) {
                return entity.javaType;
            }
        }

        throw BadRequestException("Entity Does not exist.")
    }

    /**
     * @param String construct select query from pa
     * Description: Contstruct select columns from payload.
     *
     * @return this instance
     */
    fun fieldResolver(field: String) : Any {

        val mappedPayload = this.isResourceExistWithException(this.mapPayload(field))
        val values = ObjectMapper().convertValue(mappedPayload.get("field"), object : TypeReference<List<*>>() {})
        val paths: MutableList<Path<String>> = mutableListOf()
        var query: CriteriaQuery<*>

        if (values.isNotEmpty()) {
            values.stream().forEach {
                paths.add(this.elements.get<String>(this.refactorField(it.toString())))
            }
            query = this.criteriaQuery.multiselect(*paths.toTypedArray())
        } else {
            query = (this.criteriaQuery as CriteriaQuery<Any>?)?.select(this.elements)!!
        }

        this.typeQuery = this.entityManager.createQuery(query)
        return this

    }

    /**
     * @param String construct select query from pa
     * Description: Construct select distinct columns from payload.
     *
     * @return this instance
     */
    fun distinctFieldResolver(field: String) : Any {

        val mappedPayload = this.isResourceExistWithException(this.mapPayload(field))
        val values = ObjectMapper().convertValue(mappedPayload.get("distinctField"), object : TypeReference<List<*>>() {})
        val paths: MutableList<Path<String>> = mutableListOf()
        var query: CriteriaQuery<*>

        if (values.isNotEmpty()) {
            values.stream().forEach {
                paths.add(this.elements.get<String>(this.refactorField(it.toString())))
            }
           this.criteriaQuery.multiselect(*paths.toTypedArray()).distinct(true)
        } else {
            (this.criteriaQuery as CriteriaQuery<Any>?)?.select(this.elements)!!.distinct(true)
        }

        return this
    }

    /**
     *
     *
     */
    fun distinctResolver(field: String) : Any {
        println("ss")
        this.criteriaQuery.distinct(true)
        return this
    }

    /**
     * @param String
     * Description: Remove table prefix and convert field to camelCase string
     *
     * @return String
     */
    private fun refactorField(field: String) : String {

        val preFix = this.entityTablePrefix;

        return field.replaceFirst(preFix.toString(), "")
            .replace("_", " ")
            .split(" ")
            .joinToString("") {it.toString().capitalize()}
            .decapitalize()
    }

    /**
     * @param void
     * Description: Return output list of query.
     *
     * @return List of array
     */
    fun getResult() : Any {
        this.entityManager.clear()
        this.entityManager.close()

        this.typeQuery = this.entityManager.createQuery(this.criteriaQuery)
        return this.typeQuery.resultList
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
        val requiredEnum = (RepositoryResourceEnum.queryFields().map { it.name.uppercase() }.toList())

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

}