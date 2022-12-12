package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,Long> {

    UserRole findByRoleName(char[] role_name);
}
