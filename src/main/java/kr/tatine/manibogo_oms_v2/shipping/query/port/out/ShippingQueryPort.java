package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingQuery;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ShippingQueryPort {

    Page<ShippingView> findAll(ShippingQuery filter, Pageable pageable);

    Optional<ShippingView> findByNumber(ShippingNumber shippingNumber);

}
