package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ShippingJusoQueryPort extends Repository<ShippingJuso, ShippingNumber> {

    List<ShippingJuso> findAllByJusoCode(JusoCode jusoCode);

}
