package com.lookable.domain.post;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.bookmark.Bookmark;
import com.lookable.domain.heart.Heart;
import com.lookable.domain.posttag.PostTag;
import com.lookable.domain.productlink.ProductLink;
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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Embedded
    private PostFilter filter;

    @Column(nullable = false)
    @Embedded
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<View> views = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductLink> productLinks = new ArrayList<>();

    public void updatePost(String img, String description,
                           String temperature, String weather, String sensitivity,
                           String city, String district) {
        if (null != img) {
            this.img = img;
        }

        if (null != description) {
            this.description = description;
        }

        if (null != temperature) {
            this.filter.updateTemperature(temperature);
        }

        if (null != weather) {
            this.filter.updateWeather(weather);
        }

        if (null != sensitivity) {
            this.filter.updateSensitivity(sensitivity);
        }

        if (null != city || null != district) {
            this.location.updateLocation(city, district);
        }
    }
}
