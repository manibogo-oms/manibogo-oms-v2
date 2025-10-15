package kr.tatine.manibogo_oms_v2.juso.application.dto.out;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class JusoSyncedEvent extends Event {

    private final JusoCode jusoCode;

    private final LocalDateTime referenceTime;

}
