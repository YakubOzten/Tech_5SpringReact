package com.yakubozten.tech5.controller.api.impl;

import com.yakubozten.tech5.business.dto.RoleDto;
import com.yakubozten.tech5.business.services.IRolesUserService;
import com.yakubozten.tech5.controller.api.IRolesUserApi;
import com.yakubozten.tech5.data.repository.IRegisterRepository;
import com.yakubozten.tech5.error.ApiResult;
import com.yakubozten.tech5.utils.FrontendPortUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Log4j2

@RestController
@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/roles/api/v1")
public class RolesUserApiImpl  implements IRolesUserApi {
    private final IRolesUserService iRolesUserService;
    private final IRegisterRepository iUserRepository;

    // ERROR
    private ApiResult apiResult;

    // ROLES CREATE
    // http://localhost:2222/roles/api/v1/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<RoleDto> rolesCreate(@Valid @RequestBody RoleDto roleDto) {
        //Sisteme olan kullancılar
        /**
         * Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         * if(authentication!=null && authentication.isAuthenticated()){
         * System.out.println(authentication.getName());
         * System.out.println(authentication.getPrincipal());
         * }*/
        return ResponseEntity.ok(iRolesUserService.rolesCreate(roleDto));
    }
    // ROLES LIST
    // http://localhost:2222/roles/api/v1/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RoleDto>> rolesList() {return ResponseEntity.ok(iRolesUserService.rolesList());

    }
    // FIND
    // http://localhost:2222/roles/api/v1/find
    // http://localhost:2222/roles/api/v1/find/0
    // http://localhost:2222/roles/api/v1/find/-1
    // http://localhost:2222/roles/api/v1/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> rolesApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult(400, "localhost:2222/blog/api/v1/blog/0", "Kötü istek", "Bad Request");
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/blog/api/v1/blog/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(iRolesUserService.rolesFind(id));
        return ResponseEntity.ok(iRolesUserService.rolesFind(id));
    }

    @Override
    public ResponseEntity<RoleDto> rolesUpdate(Long id, RoleDto roleDto) {
        return null;
    }

    @Override
    public ResponseEntity<RoleDto> userEmailFindRoles(String emailAddress) {
        return null;
    }

    @Override
    public ResponseEntity<?> rolesDelete(Long id) {
        return null;
    }
}
