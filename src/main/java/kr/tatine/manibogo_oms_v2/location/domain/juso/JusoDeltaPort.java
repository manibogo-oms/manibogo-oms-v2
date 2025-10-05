package kr.tatine.manibogo_oms_v2.location.domain.juso;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface JusoDeltaPort {
    List<Juso> fetch(LocalDate from);

}
