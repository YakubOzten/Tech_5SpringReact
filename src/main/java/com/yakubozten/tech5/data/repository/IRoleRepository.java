package com.yakubozten.tech5.data.repository;

import com.yakubozten.tech5.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
    // Delivered Query
    // database rol adını bulmak için kullanıyoruz.
    Optional<RoleEntity> findByRoleName(String roleName);

    //  karmaşık sorgularda Query yazıyoruz.
    // @Query
    // RoleEntity userEmailFindRoles(@Param("email") String email);
}
