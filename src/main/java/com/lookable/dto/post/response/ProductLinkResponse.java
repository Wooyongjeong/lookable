package com.lookable.dto.post.response;

import com.lookable.domain.productlink.ProductLink;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductLinkResponse {

    private Long id;

    private String name;

    private String link;

    public static ProductLinkResponse fromEntity(ProductLink productLink) {
        return ProductLinkResponse.builder()
                .id(productLink.getId())
                .name(productLink.getName())
                .link(productLink.getLink())
                .build();
    }
}
