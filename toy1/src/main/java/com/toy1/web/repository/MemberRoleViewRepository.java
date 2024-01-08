package com.toy1.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy1.web.entity.MemberRoleView;
import com.toy1.web.entity.PrimaryKeys;

@Repository
public interface MemberRoleViewRepository extends JpaRepository<MemberRoleView, PrimaryKeys> {

	List<MemberRoleView> findAllByPrimaryKeyMemberId(Long memberId);

}
