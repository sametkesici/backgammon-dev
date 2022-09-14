package com.backgammon.v1.user;

import com.backgammon.v1.user.model.Role;
import com.backgammon.v1.user.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

  Role findByRoleName(RoleType roleType);

}
