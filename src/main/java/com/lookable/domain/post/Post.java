package com.lookable.domain.post;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.bookmark.Bookmark;
import com.lookable.domain.heart.Heart;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.user.User;
import com.lookable.domain.view.View;
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

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<PostTag> postTags = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<View> views = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Heart> hearts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ProductLink> productLinks = new ArrayList<>();

}
