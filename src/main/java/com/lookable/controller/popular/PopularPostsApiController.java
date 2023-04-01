package com.lookable.controller.popular;

import com.lookable.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PopularPostsApiController {

    private final PostService postService;

}
