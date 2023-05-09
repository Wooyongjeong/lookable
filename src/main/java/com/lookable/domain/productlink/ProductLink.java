package com.lookable.domain.productlink;

import com.lookable.domain.BaseEntity;
import com.lookable.domain.post.Post;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductLink extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 65535)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void linkToPost(Post post) {
        this.post = post;
    }

}
