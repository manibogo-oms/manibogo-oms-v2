package kr.tatine.manibogo_oms_v2.logistics.query.dao;

import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsDto;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogisticsDao {

    Page<LogisticsDto> findAll(Pageable pageable, LogisticsQueryParams queryParams);

}
