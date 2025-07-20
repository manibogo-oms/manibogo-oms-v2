package kr.tatine.manibogo_oms_v2.member.query;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @Id
    private String username;

    private String password;

    private String role;

    private Boolean isEnabled;

}

