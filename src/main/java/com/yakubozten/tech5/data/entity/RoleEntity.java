package com.yakubozten.tech5.data.entity;

import com.yakubozten.tech5.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Builder

// ENTITY
@Entity(name="Roles")
@Table(name = "roles")
public class RoleEntity extends AuditingAwareBaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long rolesId;

    @Column(name = "role_name",columnDefinition = "varchar(255) default 'Role Adınızı girmediniz")
    private String roleName;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;
    //private LocalDate systemDate;



}
