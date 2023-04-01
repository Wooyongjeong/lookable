package com.lookable.controller.popular;

import com.lookable.dto.ApiResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.service.popular.PopularPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/popular")
@RestController
public class PopularPostsApiController {

    private final PopularPostService popularPostService;

    @GetMapping("/today")
    public ApiResponse<Page<PostThumbnailResponse>> popularToday(Pageable pageable) {
        LocalDate startDate = LocalDate.now();
        Page<PostThumbnailResponse> response = popularPostService.findPopularPosts(startDate, pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/weekly")
    public ApiResponse<Page<PostThumbnailResponse>> popularWeekly(Pageable pageable) {
        LocalDate startDate = LocalDate.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        Page<PostThumbnailResponse> response = popularPostService.findPopularPosts(startDate, pageable);
        return ApiResponse.success(response);
    }

    @GetMapping("/monthly")
    public ApiResponse<Page<PostThumbnailResponse>> popularMonthly(Pageable pageable) {
        LocalDate startDate = LocalDate.now()
                .withDayOfMonth(1);
        Page<PostThumbnailResponse> response = popularPostService.findPopularPosts(startDate, pageable);
        return ApiResponse.success(response);
    }

}
