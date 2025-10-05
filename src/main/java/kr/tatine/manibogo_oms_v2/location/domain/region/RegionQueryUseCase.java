package kr.tatine.manibogo_oms_v2.location.domain.region;

import java.util.List;

public interface RegionQueryUseCase {

    List<Region> findAll(int level, String parentCode);

}
