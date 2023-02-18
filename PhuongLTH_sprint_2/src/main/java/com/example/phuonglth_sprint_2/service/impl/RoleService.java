package com.example.phuonglth_sprint_2.service.impl;

import com.example.phuonglth_sprint_2.entity.account.Role;
import com.example.phuonglth_sprint_2.entity.account.RoleName;
import com.example.phuonglth_sprint_2.repository.IRoleRepository;
import com.example.phuonglth_sprint_2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
