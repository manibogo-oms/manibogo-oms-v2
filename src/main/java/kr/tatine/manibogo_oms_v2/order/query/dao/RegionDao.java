package kr.tatine.manibogo_oms_v2.order.query.dao;

import kr.tatine.manibogo_oms_v2.order.query.dto.RegionDto;

import java.util.List;

public interface RegionDao {

    List<RegionDto> findDistinctAll();

}
