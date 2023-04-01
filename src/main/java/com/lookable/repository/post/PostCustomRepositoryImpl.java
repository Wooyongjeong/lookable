package com.lookable.repository.post;

import com.lookable.domain.heart.QHeart;
import com.lookable.domain.post.*;
import com.lookable.domain.posttag.QPostTag;
import com.lookable.domain.tag.QTag;
import com.lookable.domain.user.User;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.lookable.domain.heart.QHeart.heart;
import static com.lookable.domain.post.QPost.post;
import static com.lookable.domain.posttag.QPostTag.postTag;
import static com.lookable.domain.tag.QTag.tag;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostThumbnailResponse> findPosts(PostSearchCondition cond, Pageable pageable) {
        List<PostThumbnailResponse> posts = queryFactory
                .select(Projections.constructor(PostThumbnailResponse.class,
                        post.id,
                        post.img))
                .from(post)
                .where(
                        postFilterAllEq(cond.getTemperature(), cond.getWeather(), cond.getSensitivity()),
                        locationEq(cond.getCity(), cond.getDistrict()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.createdDate))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .where(postFilterAllEq(cond.getTemperature(), cond.getWeather(), cond.getSensitivity()),
                        locationEq(cond.getCity(), cond.getDistrict()));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    @Override
    public Post findPostDetail(Long postId, User user) {
        return queryFactory.selectFrom(post)
                .join(post.postTags, postTag).fetchJoin()
                .join(postTag.tag, tag).fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();

    }

    private BooleanExpression postFilterAllEq(String temperature, String weather, String sensitivity) {
        return temperatureEq(temperature)
                .and(
                        weatherEq(weather).and(
                                sensitivityEq(sensitivity)
                        )
                );
    }

    private BooleanExpression temperatureEq(String temperature) {
        return temperature != null ? post.filter.temperature.eq(Temperature.valueOf(temperature)) : null;
    }

    private BooleanExpression weatherEq(String weather) {
        return weather != null ? post.filter.weather.eq(Weather.valueOf(weather)) : null;
    }

    private BooleanExpression sensitivityEq(String sensitivity) {
        return sensitivity != null ? post.filter.sensitivity.eq(Sensitivity.valueOf(sensitivity)) : null;
    }

    private BooleanExpression locationEq(String city, String district) {
        return cityEq(city).and(districtEq(district));
    }

    private BooleanExpression cityEq(String city) {
        return city != null ? post.location.city.eq(city) : null;
    }

    private BooleanExpression districtEq(String district) {
        return district != null ? post.location.district.eq(district) : null;
    }

}
