package com.lookable.dto.post.request;

import com.lookable.domain.productlink.ProductLink;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ProductLinkRequest {

    @NotBlank(message = "제품명을 입력해주세요.")
    private String name;

    @NotBlank(message = "제품의 링크를 입력해주세요.")
    private String link;

    public ProductLink toEntity() {
        return ProductLink.builder()
                .name(name)
                .link(link)
                .build();
    }

}
