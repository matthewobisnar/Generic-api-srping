package com.mattproject.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.mattproject.extension.GlobalUtil
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.io.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Table(name = "employee_account", schema = "employee")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class EmployeeAccountEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Uses underlying   persistence framework to generate an Id
    @Column(name = "employee_account_id")
    @JsonProperty("employee_account_id")
    var accountId: Int = 0,

    @Column(name = "employee_account_employee_code")
    @JsonProperty("employee_account_employee_code")
    var employeeCode: String,

    @Column(name = "employee_account_company_code")
    @JsonProperty("employee_account_company_code")
    var companyCode: String,

    @Column(name = "employee_account_username")
    @JsonProperty("employee_account_username")
    var username: String,

    @Column(name = "employee_account_password")
    @JsonProperty("employee_account_password")
    var password: String,

    @Column(name = "employee_account_email")
    @JsonProperty("employee_account_email")
    var email: String,

    @Column(name = "employee_account_type")
    @JsonProperty("employee_account_type")
    var type: String,

    @Column(name = "employee_account_department_code")
    @JsonProperty("employee_account_department_code")
    var departmentCode: String,

    @Column(name = "employee_account_team_code")
    @JsonProperty("employee_account_team_code")
    var teamCode: String,

    @Column(name = "employee_account_created_by")
    @JsonProperty("employee_account_created_by")
    var createdBy: String = GlobalUtil::SYSTEM_GENERATED.toString(),

    @Column(name = "employee_account_created_at")
    @JsonProperty("employee_account_created_at")
    var createdAt: String = GlobalUtil::currentDate.toString(),

    @Column(name = "employee_account_updated_by")
    @JsonProperty("employee_account_updated_by")
    var updatedBy: String? = null,

    @Column(name = "employee_account_updated_at")
    @JsonProperty("employee_account_updated_at")
    var updatedAt: String? = null,

    @Column(name = "employee_account_deleted_by")
    @JsonProperty("employee_account_deleted_by")
    var deletedBy: String? = null,

    @Column(name = "employee_account_deleted_at")
    @JsonProperty("employee_account_deleted_at")
    var deletedAt: String? = null,

//    @OneToOne
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "employee_account_employee_code",
//        referencedColumnName = "employee_account_role_employee_code",
//        insertable = false, updatable = false,
//        foreignKey = javax.persistence
//        .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
//    var employeeAccountRole: EmployeeAccountRoleEntity,

    @OneToMany(
        targetEntity = EmployeeAccountRoleEntity::class,
        cascade = arrayOf(CascadeType.ALL),
        fetch = FetchType.LAZY
    )
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(
        name = "employee_account_role_employee_code",
        referencedColumnName = "employee_account_employee_code",
        insertable = false,
        updatable = false,
        foreignKey = javax.persistence
        .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var employeeAccountRoles: List<EmployeeAccountRoleEntity>

) : Serializable {

    @Transient
    @JsonIgnore
    val table_prefix: String = "employee_account_"

    override fun toString(): String {
        return "EmployeeAccountData(" +
                "employeeCode='$employeeCode', " +
                "companyCode='$companyCode', " +
                "username='$username', " +
                "password='$password', " +
                "email='$email', " +
                "type='$type', " +
                "departmentCode='$departmentCode', " +
                "teamCode='$teamCode', " +
                "createdBy='$createdBy', " +
                "createdAt='$createdAt', " +
                "updatedBy='$updatedBy', " +
                "updatedAt='$updatedAt', " +
                "deletedBy='$deletedBy'," +
                "deletedAt='$deletedAt', " +
                ")"
    }

}