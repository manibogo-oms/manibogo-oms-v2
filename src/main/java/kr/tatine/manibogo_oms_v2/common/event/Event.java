package kr.tatine.manibogo_oms_v2.common.event;

import lombok.Getter;

@Getter
public abstract class Event {

    private long timestamp;

    public Event() {
        this.timestamp = System.currentTimeMillis();
    }

}
