package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.RegionDto;

import java.util.List;

public interface RegionDao {

    List<RegionDto> findDistinctAll();

}
