package kr.tatine.manibogo_oms_v2.juso.domain;

import kr.tatine.manibogo_oms_v2.common.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JusoCreatedEvent extends Event {

    private final JusoCode jusoCode;

    private final String admCode;

    private final String address;

    private final String sido;

    private final String sigungu;

    public JusoCreatedEvent(Juso juso) {
        this.jusoCode = juso.getCode();
        this.admCode = juso.getAdmCode();
        this.address = juso.getAddress();
        this.sido = juso.getSido();
        this.sigungu = juso.getSigungu();
    }

}
