package com.yakubozten.tech5.business.services.impl;

import com.yakubozten.tech5.bean.ModelMapperBeanClass;
import com.yakubozten.tech5.business.dto.RoleDto;
import com.yakubozten.tech5.business.services.IRolesUserService;
import com.yakubozten.tech5.data.entity.RoleEntity;
import com.yakubozten.tech5.data.repository.IRoleRepository;
import com.yakubozten.tech5.exception.HamitMizrakException;
import com.yakubozten.tech5.exception.Resource404NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICE
// Asıl iş yükünü yapan yer
@Service
public class RolesUserServicesImpl implements IRolesUserService {
    // INJECTION
    private final ModelMapperBeanClass modelMapperBean; // DTO => ENTITY
    private final IRoleRepository iRoleRepository;
    // MODEL MAPPER
    @Override
    public RoleEntity DtoToEntity(RoleDto userDto) {
        return modelMapperBean.modelMapperMethod().map(userDto, RoleEntity.class);
    }

    @Override
    public RoleDto EntityToDto(RoleEntity roleEntity) {
        return modelMapperBean.modelMapperMethod().map(roleEntity, RoleDto.class);
    }

    @Override
    public List<RoleDto> rolesList() {
        List<RoleEntity>  roleEntity=iRoleRepository.findAll();
        List<RoleDto> roleDtoList=new ArrayList<>();
        for (RoleEntity temp: roleEntity) {
            RoleDto roleDto=modelMapperBean.modelMapperMethod().map(temp,RoleDto.class);
            roleDtoList.add(roleDto);
        }
        return roleDtoList ;
    }
    /////////////////////////////////////////////
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    // hasRole ve hasAnyRole için başına ROLE_ ekle
    // Spring Security için Başında mutlaka ROLE_ olmalıdır
    // private final static String ROLE = "ROLE_";

    @Transactional // Create, delete, update için kullanmalısın
    @Override
    public RoleDto rolesCreate(RoleDto roleDto) {
        // RoleDto ==> RoleEntity
        RoleEntity roleEntity = modelMapperBean.modelMapperMethod().map(roleDto, RoleEntity.class);
        //roleEntity.setRoleName(ROLE.concat(roleEntity.getRoleName().toUpperCase()));
        roleEntity.setRoleName(roleEntity.getRoleName().toUpperCase());
        RoleEntity roleEntityData = iRoleRepository.save(roleEntity);
        // Set RoleDto
        roleDto.setId(roleEntityData.getRolesId());
        return roleDto;
    }

    // FIND
    @Override
    public RoleDto rolesFind(Long id) {
         /*
         // 1.YOL
         Optional<BlogEntity> findEntity= iBlogRepository.findById(id);
         //Model Mapper
         BlogDto findDto= EntityToDto(findEntity.get());
         //findEntity.isPresent() VEYA findEntity!=null
         if(findEntity.isPresent()){
         return findDto;
         }
         */
        boolean result=iRoleRepository.findById(id).isPresent();

        // 2.YOL
        RoleEntity roleEntity = null;
        if (id != null){ // iRoleRepository.findById(id).isPresent()
            roleEntity = iRoleRepository.findById(id)
                    .orElseThrow(() -> new Resource404NotFoundException(id + " nolu ID yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("Roles Dto Null geldi");
        }
        return EntityToDto(roleEntity);
    }

    // ROLE UPDATE
    @Override
    @Transactional // Create, delete, update için kullanmalısın
    public RoleDto rolesUpdate(Long id, RoleDto roleDto) {
        // FIND
        RoleDto roleDtoFind = rolesFind(id);
        // Dto ==> Entity
        RoleEntity roleEntity = DtoToEntity(roleDtoFind);
        if (roleEntity != null) {
            roleEntity.setRoleName(roleDto.getRoleName());
            iRoleRepository.save(roleEntity);
            // Kaydedilen verinin ID dönsün
            roleDto.setId(roleEntity.getRolesId());
            roleDto.setCreatedDate(roleEntity.getCreatedDate());
        }
        return roleDto;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Email(User) adresinde RoleName(Role) bulmak
    @Override
    public RoleDto userEmailFindRoles(String emailAddress) {
        RoleEntity roleEntity=  iRoleRepository.userEmailFindRoles(emailAddress);
        System.out.println(roleEntity);
        System.out.println(roleEntity.getRolesId());
        System.out.println(roleEntity.getRoleName());
        return modelMapperBean.modelMapperMethod().map(roleEntity,RoleDto.class);
    }

    // @ManyToMany N-M Delete
    // DELETE
    @Override
    @Transactional // create,delete,update
    public RoleDto rolesDelete(Long id) {
        // FIND
        RoleDto roleDto = rolesFind(id);
        // Dto ==> Entity
        RoleEntity roleEntity = DtoToEntity(roleDto);
        if (roleEntity != null) {
            iRoleRepository.delete(roleEntity);
        }
        return roleDto;
    }
   /* // Injection
    private final IRoleRepository iRoleRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;

    ////////////////////////////////////////////////////////////
    // MODEL MAPPER
    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(roleEntity,RoleDto.class);
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        return modelMapperBeanClass.modelMapperMethod().map(roleDto,RoleEntity.class);
    }

    @Override
    public List<RoleDto> rolesServiceSpeedData(Long key) {
        return null;
    }

    @Override
    public String rolesServiceDeleteAll() {
        return null;
    }

    @Override
    public RoleDto userEmailFindRoles(String emailAddress) {
        return null;
    }
    // CREATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RoleDto rolesServiceCreate(RoleDto roleDto) {
        if (roleDto != null) {
            RoleEntity roleEntity = dtoToEntity(roleDto);
            roleEntity.setRoleName(roleEntity.getRoleName().toUpperCase());
            iRoleRepository.save(roleEntity);
            roleDto.setId(roleEntity.getRolesId());
            roleDto.setSystemDate(roleEntity.getSystemDate());
            return roleDto;
        }
        return null;
    }

    @Override
    public List<RoleDto> rolesServiceList() {
        // Entity List
        Iterable<RoleEntity> roleEntity = iRoleRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleEntity temp : roleEntity) {
            roleDtoList.add(entityToDto(temp));
        }
        roleDtoList.forEach(System.out::println);
        return roleDtoList;
    }

    @Override
    public RoleDto rolesServiceFindById(Long id) {
        RoleEntity roleEntity = null;
        if (id != null) {
            roleEntity= iRoleRepository.findById(id)
                    .orElseThrow(() -> new Resource404NotFoundException(id + " nolu id yoktur"));
        } else if (id == null) {
            throw new HamitMizrakException("id null olarak geldi");
        }
        return entityToDto(roleEntity);
    }
    // UPDATE
    // Transaction: Create, Delete, Update
    @Override
    @Transactional
    public RoleDto rolesServiceUpdate(Long id, RoleDto roleDto) {
        // FIND
        RoleDto rolesServiceFindById = rolesServiceFindById(id);
        RoleEntity roleEntity = null;
        if (rolesServiceFindById != null) {
            roleEntity = dtoToEntity(roleDto);
            roleEntity.setRolesId(id);
            iRoleRepository.save(roleEntity);
        }
        return entityToDto(roleEntity);
    }

    @Override
    @Transactional
    public RoleDto rolesServiceDeleteById(Long id) {
        //FIND
        RoleDto rolesServiceFindById = rolesServiceFindById(id);
        RegisterEntity registerEntity = null;
        if (rolesServiceFindById != null)
            iRoleRepository.deleteById(id);
        return rolesServiceFindById;
    }*/
}
