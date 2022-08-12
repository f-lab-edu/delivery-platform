package org.flab.deliveryplatform.shop.infrastructure.persistence;

import java.util.Optional;
import org.flab.deliveryplatform.shop.domain.Shop;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShopRepository extends JpaRepository<Shop, Long> {

    @EntityGraph(
        attributePaths = {"menus", "menus.optionGroups", "menus.optionGroups.options"},
        type = EntityGraphType.LOAD
    )
    Optional<Shop> findById(Long id);
}
