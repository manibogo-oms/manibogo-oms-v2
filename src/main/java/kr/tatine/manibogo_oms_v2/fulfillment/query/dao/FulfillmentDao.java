package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FulfillmentDao extends JpaRepository<FulfillmentDto, String> {

}
