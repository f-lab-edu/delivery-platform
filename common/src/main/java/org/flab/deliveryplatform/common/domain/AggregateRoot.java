package org.flab.deliveryplatform.common.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.flab.deliveryplatform.common.event.Event;

public class AggregateRoot {

    private final transient List<Event> domainEvents = new ArrayList<>();

    protected Event registerEvent(Event event) {
        this.domainEvents.add(event);
        return event;
    }

    public Collection<Event> getOccurredEvents() {
        List<Event> occurredEvents = domainEvents.stream()
            .collect(Collectors.toUnmodifiableList());

        domainEvents.clear();

        return occurredEvents;
    }
}
