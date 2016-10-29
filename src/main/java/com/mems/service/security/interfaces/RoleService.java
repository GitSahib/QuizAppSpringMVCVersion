package com.mems.service.security.interfaces;

import java.util.List;

import com.mems.domain.Role;

public interface RoleService {
    void save(Role role);
    Role findRoleByName(String name);
	List<Role> findAll();
}
