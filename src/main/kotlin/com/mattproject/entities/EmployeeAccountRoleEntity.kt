package com.mattproject.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Immutable
import java.io.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employee_account_role", schema = "employee")
@JsonInclude(JsonInclude.Include.ALWAYS)
class EmployeeAccountRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Uses underlying   persistence framework to generate an Id
    @Column(name = "employee_account_role_id")
    @JsonProperty("employee_account_role_id")
    var roleId: Int = 0,

    @Column(name = "employee_account_role_employee_code")
    @JsonProperty("employee_account_role_employee_code")
    var roleEmployeeCode: String,

    @Column(name = "employee_account_role_type_code")
    @JsonProperty("employee_account_role_type_code")
    var roleTypeCode: String,

    @Column(name = "employee_account_role_created_by")
    @JsonProperty("employee_account_role_created_by")
    var roleCreatedBy: String,

    @Column(name = "employee_account_role_created_at")
    @JsonProperty("employee_account_role_created_at")
    var roleCreatedAt: String,

    @Column(name = "employee_account_role_updated_by")
    @JsonProperty("employee_account_role_updated_by")
    var roleUpdatedBy: String? = null,

    @Column(name = "employee_account_role_updated_at")
    @JsonProperty("employee_account_role_updated_at")
    var roleUpdatedAt: String? = null,

    @Column(name = "employee_account_role_deleted_by")
    @JsonProperty("employee_account_role_deleted_by")
    var roleDeleletedBy: String? = null,

    @Column(name = "employee_account_role_deleted_at")
    @JsonProperty("employee_account_role_deleted_at")
    var roleDeleletedAt: String? = null,

    ): Serializable {

        @Transient
        @JsonIgnore
        val table_prefix: String = "employee_account_role_"

        override fun toString(): String {
            return "EmployeeAccountRoleData(" +
                    "roleEmployeeCode='$roleEmployeeCode', " +
                    "roleTypeCode='$roleTypeCode', " +
                    "roleCreatedBy='$roleCreatedBy', " +
                    "roleCreatedAt='$roleCreatedAt', " +
                    "roleUpdatedBy=$roleUpdatedBy, " +
                    "roleUpdatedAt=$roleUpdatedAt, " +
                    "roleDeleletedBy=$roleDeleletedBy, " +
                    "roleDeleletedAt=$roleDeleletedAt, " +
//                    "employeeAccount=$employeeAccount" +
                    ")"
        }

}