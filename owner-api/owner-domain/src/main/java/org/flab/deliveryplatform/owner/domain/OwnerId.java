package org.flab.deliveryplatform.owner.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerId implements Serializable {

    @Column(name = "owner_id")
    private Long id;

    public OwnerId(Long id) {
        this.id = id;
    }

    public static OwnerId of(Long id) {
        return new OwnerId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnerId ownerId = (OwnerId) o;
        return Objects.equals(getId(), ownerId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
