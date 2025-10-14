package kr.tatine.manibogo_oms_v2.region.application.port.in;

import kr.tatine.manibogo_oms_v2.region.domain.Region;

import java.util.List;

public interface RegionQueryUseCase {

    List<Region> findAll(int level, String parentCode);

}
