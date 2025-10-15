package kr.tatine.manibogo_oms_v2.shipping.query.port.out;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.shipping.query.entity.ShippingJuso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShippingJusoQueryPort extends Repository<ShippingJuso, ShippingNumber> {

    @Query("SELECT MAX(sj.lastModifiedAt) FROM ShippingJuso sj")
    Optional<LocalDateTime> findLastSyncTime();

    @Query("SELECT sj FROM ShippingJuso sj WHERE sj.jusoCode in :jusoCodes")
    List<ShippingJuso> findAllByJusoCodes(List<JusoCode> jusoCodes);

}
