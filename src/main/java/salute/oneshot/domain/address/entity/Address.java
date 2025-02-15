package salute.oneshot.domain.address.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.entity.User;

@Entity
@Table(name = "address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "post_address")
    private String postAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "extra_address")
    private String extraAddress;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_default")
    @ColumnDefault("false")
    private boolean isDefault = false;

    private Address(
            String addressName,
            String postcode,
            String postAddress,
            String detailAddress,
            String extraAddress,
            Long userId
    ) {
        this.addressName = addressName;
        this.postcode = postcode;
        this.postAddress = postAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.userId = userId;
    }

    public static Address of(
            String addressName,
            String postcode,
            String postAddress,
            String detailAddress,
            String extraAddress,
            Long userId
    ) {
        return new Address(
                addressName,
                postcode,
                postAddress,
                detailAddress,
                extraAddress,
                userId
        );
    }

    public void setDefault() {
        this.isDefault = true;
    }

    public void updateAddress(
            String addressName,
            String postcode,
            String postAddress,
            String detailAddress,
            String extraAddress,
            Long userId
    ) {
        this.addressName = addressName;
        this.postcode = postcode;
        this.postAddress = postAddress;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.userId = userId;
    }
}
