package com.toy1.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy1.web.entity.MemberRoleView;
import com.toy1.web.entity.PrimaryKeys;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRoleView, PrimaryKeys> {

	List<MemberRoleView> findAllByPrimaryKeyMemberId(Long memberId);
	
//	@Query(value = "SELECT " +
//            "mr.member_id, " +
//            "mr.role_id, " +
//            "r.name AS role_name " +
//            "FROM member_role mr " +
//            "LEFT JOIN role r ON mr.role_id = r.id", 
//    nativeQuery = true)
//	List<MemberRoleView> findAllByMemberId(Long id);
}
