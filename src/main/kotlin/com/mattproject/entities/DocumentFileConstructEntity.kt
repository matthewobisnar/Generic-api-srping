package com.mattproject.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonInclude
import com.mattproject.extension.GlobalUtil
import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Table(name = "document_file", schema = "utilities")
@JsonInclude(JsonInclude.Include.ALWAYS)
class DocumentFileConstructEntity: Serializable {

    constructor() {}

    constructor(
        code: String?,
        name: String?,
        type: String?,
        value: String?,
        status: Boolean,
        documentType: String?,
        documentTypeName: String?,
        createdBy: String,
        createdAt: String,
        updatedBy: String?,
        updatedAt: String?,
        deletedBy: String?,
        deletedAt: String?
    ) {
        this.code = code
        this.name = name
        this.type = type
        this.value = value
        this.status = status
        this.documentType = documentType
        this.documentTypeName = documentTypeName
        this.createdBy = createdBy
        this.createdAt = createdAt
        this.updatedBy = updatedBy
        this.updatedAt = updatedAt
        this.deletedBy = deletedBy
        this.deletedAt = deletedAt
    }

    @javax.persistence.Transient
    @JsonIgnore
    val tablePrefix: String = "document_file_"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Uses underlying   persistence framework to generate an Id
    @Column(name = "document_file_id")
    @JsonProperty("document_file_id")
    var id: Int = 0

    @Column(name = "document_file_code")
    @JsonProperty("document_file_code")
    var code: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_name")
    @JsonProperty("document_file_name")
    var name: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_type")
    @JsonProperty("document_file_type")
    var type: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_value")
    @JsonProperty("document_file_value")
    var value: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_status")
    @JsonProperty("document_file_status")
    var status: Boolean = true
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_document_type")
    @JsonProperty("document_file_document_type")
    var documentType: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_document_type_name")
    @JsonProperty("document_file_document_type_name")
    var documentTypeName: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_created_by")
    @JsonProperty("document_file_created_by")
    var createdBy: String = GlobalUtil::SYSTEM_GENERATED.toString()
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_created_at")
    @JsonProperty("document_file_created_at")
    var createdAt: String = GlobalUtil::currentDate.toString()
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_updated_by")
    @JsonProperty("document_file_updated_by")
    var updatedBy: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_updated_at")
    @JsonProperty("document_file_updated_at")
    var updatedAt: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_deleted_by")
    @JsonProperty("document_file_deleted_by")
    var deletedBy: String? = null
        get() = field
        set(value) { field = value }

    @Column(name = "document_file_deleted_at")
    @JsonProperty("document_file_deleted_at")
    var deletedAt: String? = null
        get() = field
        set(value) { field = value }

    fun tablePrefix(): String = "document_file_"

    override fun toString(): String {
        return "DocumentFileConstructEntity(code=$code, fileName=$name, type=$type, value=$value, status=$status, documentType=$documentType, documentTypeName=$documentTypeName, createdBy='$createdBy', createdAt='$createdAt', updatedBy=$updatedBy, updatedAt=$updatedAt, deletedBy=$deletedBy, deletedAt=$deletedAt)"
    }
}