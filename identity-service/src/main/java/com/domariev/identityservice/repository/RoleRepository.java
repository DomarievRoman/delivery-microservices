package com.domariev.identityservice.repository;

import com.domariev.identityservice.model.Role;
import com.domariev.identityservice.model.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByAuthority(RoleAuthority authority);

}
