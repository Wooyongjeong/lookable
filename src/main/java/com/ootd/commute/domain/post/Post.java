package com.ootd.commute.domain.post;

import com.ootd.commute.domain.BaseEntity;
import com.ootd.commute.domain.heart.Heart;
import com.ootd.commute.domain.posttag.PostTag;
import com.ootd.commute.domain.user.User;
import com.ootd.commute.domain.view.View;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String img;

    private String description;

    @Embedded
    private PostFilter filter;

    @Embedded
    private Location location;

    // 제품명

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<View> views = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Heart> hearts = new ArrayList<>();

}
