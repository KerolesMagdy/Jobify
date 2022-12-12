package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserRole;

public interface UserRoleServiceOp {

    UserRole saveRole(UserRole userRole);
    UserRole getByRoleName(char[] name);
    long getRoleCount();
}
