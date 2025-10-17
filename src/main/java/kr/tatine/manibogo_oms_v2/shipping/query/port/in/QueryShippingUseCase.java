package kr.tatine.manibogo_oms_v2.shipping.query.port.in;

import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingQuery;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface QueryShippingUseCase {

    Page<ShippingView> findAll(ShippingQuery filter, Pageable pageable);

}
