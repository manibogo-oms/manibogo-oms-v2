package kr.tatine.manibogo_oms_v2.common.config;

import kr.tatine.manibogo_oms_v2.member.command.domain.Member;
import kr.tatine.manibogo_oms_v2.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    private UserDetails createUserDetails(Member member) {
        return new User(member.getUsername(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getRole())));
    }

}
