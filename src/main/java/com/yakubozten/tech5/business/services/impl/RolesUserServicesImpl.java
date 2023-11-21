package com.yakubozten.tech5.business.services.impl;

import com.yakubozten.tech5.bean.ModelMapperBeanClass;
import com.yakubozten.tech5.business.dto.RoleDto;
import com.yakubozten.tech5.business.services.IRolesUserServices;
import com.yakubozten.tech5.data.entity.RegisterEntity;
import com.yakubozten.tech5.data.entity.RoleEntity;
import com.yakubozten.tech5.data.repository.IRoleRepository;
import com.yakubozten.tech5.exception.HamitMizrakException;
import com.yakubozten.tech5.exception.Resource404NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICE
// Asıl iş yükünü yapan yer
@Service
public class RolesUserServicesImpl implements IRolesUserServices<RoleDto, RoleEntity> {

    // Injection
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
    }
}
