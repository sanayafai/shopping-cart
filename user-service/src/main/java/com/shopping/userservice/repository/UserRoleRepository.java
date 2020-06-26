package com.shopping.userservice.repository;

import com.shopping.userservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findUserRoleByRoleName(String roleName);
}
