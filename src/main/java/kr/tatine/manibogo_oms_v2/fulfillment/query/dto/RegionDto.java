package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegionDto {

    private String sido;

    private String sigungu;

    public RegionDto(String sido, String sigungu) {
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
