package kr.tatine.manibogo_oms_v2.shipping.infra.dto;

import java.util.List;

public record JusoApiSearchResponse(
        Results results
) {

    public record Results(
            Common common,
            List<Juso> juso
    ) {

        public record Common(
                String totalCount,
                Integer currentPage,
                Integer countPerPage,
                String errorCode,
                String errorMessage
        ) {}

        public record Juso(
                String roadAddr,
                String roadAddrPart1,
                String roadAddrPart2,
                String jibunAddr,
                String engAddr,
                String zipNo,
                String admCd,
                String rnMgtSn,
                String bdMgtSn,
                String detBdNmList,
                String bdNm,
                String bdKdcd,
                String siNm,
                String sggNm,
                String emdNm,
                String liNm,
                String rn,
                String udrtYn,
                String buldMnnm,
                String buldSlno,
                String mtYn,
                String lnbrMnnm,
                String lnbrSlno,
                String emdNo
        ) {}

    }

}
