package com.lookable.service.productlink;

import com.lookable.domain.post.Post;
import com.lookable.domain.productlink.ProductLink;
import com.lookable.repository.productlink.ProductLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductLinkService {

    private final ProductLinkRepository productLinkRepository;

    @Transactional
    public ProductLink createProductLink(ProductLink productLink, Post post) {
        productLink.linkToPost(post);
        return productLinkRepository.save(productLink);
    }

}
