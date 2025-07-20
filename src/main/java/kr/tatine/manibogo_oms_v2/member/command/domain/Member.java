package kr.tatine.manibogo_oms_v2.member.command.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isEnabled;

    public Member(String username, String password, Role role, Boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
    }
}
