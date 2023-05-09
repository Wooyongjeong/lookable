package com.lookable.domain.feedback;

import com.lookable.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feedback extends BaseEntity {

    private String email;

    @Column(length = 1000)
    private String content;

}
