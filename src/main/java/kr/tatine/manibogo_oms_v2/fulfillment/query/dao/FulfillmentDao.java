package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;

import java.util.List;

public interface FulfillmentDao {

    List<FulfillmentDto> findAll();

}
