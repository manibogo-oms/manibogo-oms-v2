package kr.tatine.manibogo_oms_v2.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    private Events() {}

    public static void raise(Event event) {
        if (publisher == null) return;

        publisher.publishEvent(event);
    }

}
