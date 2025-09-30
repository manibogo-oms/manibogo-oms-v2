package kr.tatine.manibogo_oms_v2.region.query.dto.out;

import jakarta.persistence.*;
import kr.tatine.manibogo_oms_v2.region.command.domain.RegionCode;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "region")
public class RegionView {

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
