package kr.tatine.manibogo_oms_v2.region.query.dao;

import kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto;
import kr.tatine.manibogo_oms_v2.region.query.dto.RegionView;

import java.util.List;

public interface RegionDao {

    List<RegionDto> findDistinctAll();

    List<RegionView> findAll(int level, String parentCode);

}
