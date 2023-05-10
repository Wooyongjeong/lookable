package com.lookable.service.popular;

import com.lookable.domain.post.Post;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PopularPostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<PostThumbnailResponse> findPopularPosts(LocalDate startDate, Pageable pageable) {
        Page<Post> postsPage = postRepository.findPopularPosts(startDate.atStartOfDay(), LocalDateTime.now(), pageable);

//        Map<Post, Double> postScoreMap = new HashMap<>();
//        for (Post post : postsPage.getContent()) {
//            double score = calculatePostScore(post);
//            postScoreMap.put(post, score);
//        }
//
//        List<Post> posts = postScoreMap.entrySet().stream()
//                .sorted(Map.Entry.<Post, Double>comparingByValue().reversed())
//                .map(Map.Entry::getKey).toList();

        List<PostThumbnailResponse> response = postsPage.getContent().stream()
                .map(PostThumbnailResponse::fromEntity)
                .toList();

        return new PageImpl<>(response, postsPage.getPageable(), postsPage.getTotalElements());
    }

    private double calculatePostScore(Post post) {
        LocalDateTime createdDate = post.getCreatedAt();
        Duration duration = Duration.between(createdDate, LocalDateTime.now());
        double timeDecayFactor = calculateTimeDecayFactor(duration);

        return timeDecayFactor * (post.getHearts().size() + post.getViews().size() * 0.1);
    }

    private double calculateTimeDecayFactor(Duration duration) {
        double decayBase = 1.05;
        long seconds = duration.getSeconds();
        return Math.pow(decayBase, -seconds / 3600.0);
    }

}
