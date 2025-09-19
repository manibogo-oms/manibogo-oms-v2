package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingOrderAggView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ShippingOrderAggQueryPort extends Repository<ShippingOrderAggView, String> {

    @Query("SELECT sao FROM ShippingOrderAggView sao WHERE sao.shippingNumber in :shippingNumbers")
    List<ShippingOrderAggView> findByShippingNumbers(List<ShippingNumber> shippingNumbers);

    ShippingOrderAggView save(ShippingOrderAggView shippingOrderAggView);

}
