package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FulfillmentDao {

    Page<FulfillmentDto> findAll(Pageable pageable, FulfillmentQueryParams queryParams);

}
