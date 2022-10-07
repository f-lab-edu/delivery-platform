package org.flab.deliveryplatform.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.event.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AggregateRootTest {

    private AggregateRoot aggregateRoot;

    @BeforeEach
    void setUp() {
        aggregateRoot = new AggregateRoot();
    }

    @Test
    void getOccurredEvents() {
        aggregateRoot.registerEvent(new FakeEvent(1L));
        aggregateRoot.registerEvent(new FakeEvent(2L));

        Collection<Event> occurredEvents = aggregateRoot.getOccurredEvents();

        assertThat(occurredEvents).hasSize(2);
        assertThatThrownBy(() -> occurredEvents.add(new FakeEvent(3L)))
            .isInstanceOf(UnsupportedOperationException.class);

        Collection<Event> emptyEvents = aggregateRoot.getOccurredEvents();
        assertThat(emptyEvents).hasSize(0);
    }

    @RequiredArgsConstructor
    static class FakeEvent extends Event {

        private final Long id;
    }
}
