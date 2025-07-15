package kr.tatine.manibogo_oms_v2.order.query.dao;

import kr.tatine.manibogo_oms_v2.order.query.dto.OrderDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    Page<OrderDto> findAll(Pageable pageable, OrderQueryParams queryParams);

    List<OrderDto> findAll(OrderQueryParams queryParams);

    Optional<OrderDto> findById(String orderNumber);

}
