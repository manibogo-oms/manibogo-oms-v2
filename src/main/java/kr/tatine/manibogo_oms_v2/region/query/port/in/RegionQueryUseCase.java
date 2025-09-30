package kr.tatine.manibogo_oms_v2.region.query.port.in;

import kr.tatine.manibogo_oms_v2.region.query.dto.out.RegionView;

import java.util.List;

public interface RegionQueryUseCase {

    List<RegionView> findAll(int level, String parentCode);

}
