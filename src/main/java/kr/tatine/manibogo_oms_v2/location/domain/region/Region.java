package kr.tatine.manibogo_oms_v2.location.domain.region;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
public class Region {

    @Id
    private RegionCode code;

    private Integer level;

    @Embedded
    @AttributeOverride(
            name = "code", column = @Column(name = "parent_code"))
    private RegionCode parentCode;

    private String fullName;

    private String shortName;

}
