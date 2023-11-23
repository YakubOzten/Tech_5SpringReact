package com.yakubozten.tech5.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.Map;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
// spring Frameworkta gelen Error'ları kendimize göre yakalamak
// (Jackson: objeyi json'a çevirir)
// Eğer sistemde null değer varsa backentte gönderme
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    // Pırasa Vali MESC
    private String path;
    private String message;
    private String error;
    private int status;
    //tarih
    private Date createdDate=new Date(System.currentTimeMillis());
    //validationError
    private Map<String,String> validationErrors;

    // parametreli constructor
    public ApiResult(int status, String path, String message, String error) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.error = error;
    }

    //Parametreli constructor
    public ApiResult(int status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
    }
} //end class
