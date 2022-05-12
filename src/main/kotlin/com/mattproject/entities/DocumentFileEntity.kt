package com.mattproject.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonIgnore
import com.mattproject.extension.GlobalUtil
import org.hibernate.annotations.Immutable
import kotlin.jvm.Transient
import javax.persistence.*
import java.io.*

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Table(name = "document_file", schema = "utilities")
@JsonInclude(JsonInclude.Include.ALWAYS)
class DocumentFileEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Uses underlying   persistence framework to generate an Id
    @Column(name = "document_file_id")
    @JsonProperty("document_file_id")
    var id: Int = 0,

    @Column(name = "document_file_code")
    @JsonProperty("document_file_code")
    var code: String,

    @Column(name = "document_file_name")
    @JsonProperty("document_file_name")
    var name: String,

    @Column(name = "document_file_type")
    @JsonProperty("document_file_type")
    var type: String,

    @Column(name = "document_file_value")
    @JsonProperty("document_file_value")
    var value: String?,

    @Column(name = "document_file_status")
    @JsonProperty("document_file_status")
    var status: Boolean = true,

    @Column(name = "document_file_document_type")
    @JsonProperty("document_file_document_type")
    var documentType: String?,

    @Column(name = "document_file_document_type_name")
    @JsonProperty("document_file_document_type_name")
    var documentTypeName: String?,

    @Column(name = "document_file_created_by")
    @JsonProperty("document_file_created_by")
    var createdBy: String = GlobalUtil::SYSTEM_GENERATED.toString(),

    @Column(name = "document_file_created_at")
    @JsonProperty("document_file_created_at")
    var createdAt: String = GlobalUtil::currentDate.toString(),

    @Column(name = "document_file_updated_by")
    @JsonProperty("document_file_updated_by")
    var updatedBy: String? = null,

    @Column(name = "document_file_updated_at")
    @JsonProperty("document_file_updated_at")
    var updatedAt: String? = null,

    @Column(name = "document_file_deleted_by")
    @JsonProperty("document_file_deleted_by")
    var deletedBy: String? = null,

    @Column(name = "document_file_deleted_at")
    @JsonProperty("document_file_deleted_at")
    var deletedAt: String? = null,

) : Serializable {

    fun tablePrefix(): String = "document_file_"

    override fun toString(): String {
        return "DocumentFileEntity(" +
                "Id='$id', " +
                "code='$code', " +
                "name='$name', " +
                "type='$type', " +
                "value=$value, " +
                "status=$status, " +
                "documentType=$documentType, " +
                "documentTypeName=$documentTypeName, " +
                "createdBy='$createdBy', " +
                "createdAt='$createdAt', " +
                "updatedBy=$updatedBy, " +
                "updatedAt=$updatedAt, " +
                "deletedBy=$deletedBy, " +
                "deletedAt=$deletedAt)"
    }
}