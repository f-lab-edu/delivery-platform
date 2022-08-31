package org.flab.deliveryplatform.server.event.outbox;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOutBoxRepository extends JpaRepository<OutBox, Long> {

    void deleteAllByIdIn(List<Long> outBoxIds);
}
