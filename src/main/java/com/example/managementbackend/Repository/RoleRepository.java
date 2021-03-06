package com.example.managementbackend.Repository;


import com.example.managementbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
     Role findByName(String RoleName);
    List<Role> findRoleByUsersId(Long userId );
    List<Role>findByUsersId(Long id);


}

