package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Model.Entity.UserRole;
import com.keroles.jobify.Repository.UserRoleRepo;
import com.keroles.jobify.Service.Operation.UserRoleServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements UserRoleServiceOp {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserRole saveRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public UserRole getByRoleName(char[] name) {
        return userRoleRepo.findByRoleName(name);
    }

    @Override
    public long getRoleCount() {
        return userRoleRepo.count();
    }


}
