package com.example.phuonglth_sprint_2.repository;

import com.example.phuonglth_sprint_2.entity.account.Role;
import com.example.phuonglth_sprint_2.entity.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
