package org.flab.deliveryplatform.owner.domain;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @EmbeddedId
    private OwnerId id;

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    @Builder
    public Owner(OwnerId id, String nickname, String email, String password,
        String phoneNumber) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
