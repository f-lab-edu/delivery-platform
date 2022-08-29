package org.flab.deliveryplatform.common.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AggregateRoot {

    private final transient List<Object> domainEvents = new ArrayList<>();

    protected Object registerEvent(Object event) {
        this.domainEvents.add(event);
        return event;
    }

    public Collection<Object> getOccurredEvents() {
        List<Object> occurredEvents = domainEvents.stream()
            .collect(Collectors.toUnmodifiableList());

        domainEvents.clear();

        return occurredEvents;
    }
}
