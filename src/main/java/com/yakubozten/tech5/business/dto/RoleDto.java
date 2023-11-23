package com.yakubozten.tech5.business.dto;

import com.yakubozten.tech5.annotation.AnnotationRoleNameUnique;
import com.yakubozten.tech5.audit.AuditingAwareBaseDto;
import com.yakubozten.tech5.rolles.ERolles;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@Builder

public class RoleDto  extends AuditingAwareBaseDto implements Serializable {
    // Serileştirme
    public static final long serialVersionUID=1L;

    // ROLE ID
    private Long rolesId;

    // ROLE NAME
    // Eğer Bir kullanıcı Admin belirlememişse Bu kullanıcı USER olduk
    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @AnnotationRoleNameUnique // Kendi Annotation RolName yazdım
    @Builder.Default
    private String roleName= ERolles.USER.toString();
}//end class
