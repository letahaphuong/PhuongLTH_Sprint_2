package com.example.phuonglth_sprint_2.service;

import com.example.phuonglth_sprint_2.entity.account.Role;
import com.example.phuonglth_sprint_2.entity.account.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);

}
