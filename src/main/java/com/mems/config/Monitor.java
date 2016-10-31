package com.mems.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mems.domain.Role;
import com.mems.service.security.interfaces.RoleService;

@Component
public class Monitor {
    @Autowired private RoleService roleService;

    @PostConstruct
    public void init(){
        // start your monitoring in here
    	List<Role> roleList = roleService.findAll();
		if (roleList.size() < 3) {
			Role role = new Role();
			role.setName("Admin");
			roleService.save(role);

			role = new Role();
			role.setName("Student");
			roleService.save(role);

			role = new Role();
			role.setName("Teacher");
			roleService.save(role);
		}
    }
}