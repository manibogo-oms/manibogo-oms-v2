package kr.tatine.manibogo_oms_v2.juso.application.port.out;

import kr.tatine.manibogo_oms_v2.juso.domain.Juso;
import org.springframework.data.repository.Repository;

public interface JusoStorePort extends Repository<Juso, String> {

    Juso save(Juso juso);

    void saveAll(Iterable<Juso> jusoes);

}
