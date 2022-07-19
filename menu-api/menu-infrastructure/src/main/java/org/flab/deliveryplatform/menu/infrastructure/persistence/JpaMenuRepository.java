package org.flab.deliveryplatform.menu.infrastructure.persistence;

import org.flab.deliveryplatform.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuRepository extends JpaRepository<Menu, Long> {

}
