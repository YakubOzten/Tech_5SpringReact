package com.yakubozten.tech5.business.dto;

import com.yakubozten.tech5.annotation.AnnotationUniqueEmailAddress;
import com.yakubozten.tech5.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
/*@Data*/
@Log4j2
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// REGISTER
public class RegisterDto extends AuditingAwareBaseDto implements Serializable {




    // Serileştirme
    public static final Long serialVersionUID=1L;
    // Global Variable (6)
    // Dikkat: message sonunda boşluk olmasın
    @NotEmpty(message = "{register.nickname.validation.constraints.NotNull.message}")
    private  String registerNickname;

    @NotEmpty(message = "{register.name.validation.constraints.NotNull.message}")
    private String registerName;

    @NotEmpty(message = "{register.surname.validation.constraints.NotNull.message}")
    private String registerSurname;

    // Kendi annotation'ımı yazdı
    @AnnotationUniqueEmailAddress
    @NotEmpty(message = "{register.email.validation.constraints.NotNull.message}")
    @Email(message = "{register.email.validation.constraints.regex.message}")
    private String registerEmail;

    @NotEmpty(message = "{register.password.validation.constraints.NotNull.message}")
    private String registerPassword;

    @Builder.Default //default olarak kullanıcı pasif olsun admin bunu aktif yapsın
    private Boolean registerIsPassive=false;





}
