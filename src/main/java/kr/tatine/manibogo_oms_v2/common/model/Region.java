package kr.tatine.manibogo_oms_v2.common.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipCode;

    private String sido;

    private String sigungu;

    public Region(String zipCode, String sido, String sgg) {
        this.zipCode = zipCode;
        this.sido = sido;
        this.sigungu = sgg;
    }
}
