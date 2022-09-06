package com.artzvrzn.authorization.dao;

import com.artzvrzn.authorization.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  @Query("select case when count(r) > 0 then true else false end from Role r "
      + "where r.name = 'ROLE_USER' or r.name = 'ROLE_ADMIN'")
  boolean isMainRolesPresent();
}
