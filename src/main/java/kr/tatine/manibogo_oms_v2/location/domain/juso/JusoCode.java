package kr.tatine.manibogo_oms_v2.location.domain.juso;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JusoCode {

    private String code;

    public JusoCode(String code) {
        this.code = code;
    }

    public static JusoCode of(String sggNo, String emdNo, String roadNo, String udrtYn, String mainBnNo, String subBnNo) {
        return new JusoCode(
                "%5s%3s%7s%1s%5s%5s".formatted(sggNo, emdNo, roadNo, udrtYn, mainBnNo, subBnNo)
        );
    }

}
