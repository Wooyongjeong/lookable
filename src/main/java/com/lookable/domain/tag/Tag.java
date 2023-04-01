package com.lookable.domain.tag;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.posttag.PostTag;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag extends BaseEntity {

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTags = new ArrayList<>();

}
