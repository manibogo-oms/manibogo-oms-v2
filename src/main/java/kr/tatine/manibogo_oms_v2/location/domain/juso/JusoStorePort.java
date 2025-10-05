package kr.tatine.manibogo_oms_v2.location.domain.juso;

import org.springframework.data.repository.Repository;

public interface JusoStorePort extends Repository<Juso, String> {

    Juso save(Juso juso);

}
