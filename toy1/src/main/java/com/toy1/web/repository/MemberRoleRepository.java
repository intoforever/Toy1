package com.toy1.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy1.web.entity.MemberRole;
import com.toy1.web.entity.PrimaryKeys;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, PrimaryKeys>{

}
