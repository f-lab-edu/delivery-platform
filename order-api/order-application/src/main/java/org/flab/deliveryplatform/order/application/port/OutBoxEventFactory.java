package org.flab.deliveryplatform.order.application.port;

import java.util.List;
import org.flab.deliveryplatform.common.event.OutBoxEvent;

public interface OutBoxEventFactory<T> {

    List<OutBoxEvent> createOutBoxEvents(T domain);
}
