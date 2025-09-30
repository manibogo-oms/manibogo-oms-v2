package kr.tatine.manibogo_oms_v2.region.query.port.out;

import kr.tatine.manibogo_oms_v2.region.query.entity.Juso;
import org.springframework.data.repository.Repository;

public interface JusoStorePort extends Repository<Juso, String> {

    Juso save(Juso juso);

}
