package kr.tatine.manibogo_oms_v2.member.command.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private String username;

    private String password;

    private String role;

    public Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
