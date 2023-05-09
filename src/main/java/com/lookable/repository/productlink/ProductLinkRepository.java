package com.lookable.repository.productlink;

import com.lookable.domain.productlink.ProductLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLinkRepository extends JpaRepository<ProductLink, Long> {
}
