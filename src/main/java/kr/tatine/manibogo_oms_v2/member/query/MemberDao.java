package kr.tatine.manibogo_oms_v2.member.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<MemberDto, String> {

    Page<MemberDto> findAll(Pageable pageable);

}
