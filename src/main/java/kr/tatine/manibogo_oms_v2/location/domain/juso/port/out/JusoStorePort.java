package kr.tatine.manibogo_oms_v2.location.domain.juso.port.out;

import kr.tatine.manibogo_oms_v2.location.domain.juso.Juso;
import org.springframework.data.repository.Repository;

public interface JusoStorePort extends Repository<Juso, String> {

    Juso save(Juso juso);

    void saveAll(Iterable<Juso> jusoes);

}
