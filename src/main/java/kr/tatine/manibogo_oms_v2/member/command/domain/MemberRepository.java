package kr.tatine.manibogo_oms_v2.member.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> { }
