package kr.tatine.manibogo_oms_v2.region.query.dao;

import kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto;

import java.util.List;

public interface RegionDao {

    List<RegionDto> findDistinctAll();

}
