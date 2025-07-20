package kr.tatine.manibogo_oms_v2.common.config;

import kr.tatine.manibogo_oms_v2.member.query.MemberDao;
import kr.tatine.manibogo_oms_v2.member.query.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class MemberUserDetailsService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberDao.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    private UserDetails createUserDetails(MemberDto memberDto) {
        return User.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .authorities(new SimpleGrantedAuthority(memberDto.getRole()))
                .disabled(!memberDto.getIsEnabled())
                .build();
    }

}
