package kr.tatine.manibogo_oms_v2.region.command.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "IDX_ZIP_CODE", columnList = "zip_code"))
public class ZipCodeRegion {

    @Id
    private String zipCode;

    private String sido;

    private String sigungu;
}
