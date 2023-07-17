package com.lookable.domain.post;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Location {

    private String city;

    private String district;

    public void updateLocation(String city, String district) {
        if (null != city) {
            this.city = city;
        }

        if (null != district) {
            this.district = district;
        }
    }
}
