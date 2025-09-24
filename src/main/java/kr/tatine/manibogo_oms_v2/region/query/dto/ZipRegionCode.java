package kr.tatine.manibogo_oms_v2.region.query.dto;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ZipRegionCode implements Serializable {

    private String zipCode;

    private String regionCode;

}
