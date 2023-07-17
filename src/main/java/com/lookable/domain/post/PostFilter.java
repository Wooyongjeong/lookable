package com.lookable.domain.post;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PostFilter {

    @Enumerated(EnumType.STRING)
    private Temperature temperature;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    @Enumerated(EnumType.STRING)
    private Sensitivity sensitivity;

    public void updateTemperature(String temperature) {
        this.temperature = Temperature.valueOf(temperature);
    }

    public void updateWeather(String weather) {
        this.weather = Weather.valueOf(weather);
    }

    public void updateSensitivity(String sensitivity) {
        this.sensitivity = Sensitivity.valueOf(sensitivity);
    }

}
