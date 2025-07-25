package kr.tatine.manibogo_oms_v2.logistics.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LogisticsQueryParams {

    private DetailSearchParam detailSearchParam;

    private String detailSearch;

    private String sido;

    private String sigungu;

}
