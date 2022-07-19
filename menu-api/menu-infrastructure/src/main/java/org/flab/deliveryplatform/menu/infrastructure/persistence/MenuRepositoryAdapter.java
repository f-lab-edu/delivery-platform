package org.flab.deliveryplatform.menu.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.domain.Menu;
import org.flab.deliveryplatform.menu.appliciation.port.MenuRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MenuRepositoryAdapter implements MenuRepository {

    private final JpaMenuRepository jpaMenuRepository;

    @Override
    public Menu save(Menu menu) {
        return jpaMenuRepository.save(menu);
    }
}
