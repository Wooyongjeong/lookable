package com.lookable.repository.post;

import com.lookable.domain.bookmark.Bookmark;
import com.lookable.domain.heart.Heart;
import com.lookable.domain.post.*;
import com.lookable.domain.productlink.QProductLink;
import com.lookable.domain.user.User;
import com.lookable.dto.post.request.PostSearchCondition;
import com.lookable.dto.post.response.PostDetailResponse;
import com.lookable.dto.post.response.PostThumbnailResponse;
import com.lookable.dto.post.response.ProductLinkResponse;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.lookable.domain.bookmark.QBookmark.bookmark;
import static com.lookable.domain.heart.QHeart.heart;
import static com.lookable.domain.post.QPost.post;
import static com.lookable.domain.posttag.QPostTag.postTag;
import static com.lookable.domain.productlink.QProductLink.productLink;
import static com.lookable.domain.tag.QTag.tag;
import static com.lookable.domain.user.QUser.user;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostThumbnailResponse> findPosts(PostSearchCondition cond, Pageable pageable) {
        List<PostThumbnailResponse> posts = queryFactory
                .select(Projections.constructor(PostThumbnailResponse.class,
                        post.id,
                        post.img,
                        post.description,
                        post.user.nickname.nickname,
                        post.user.profileImg,
                        post.createdAt))
                .from(post)
                .join(post.user, user)
                .where(
                        postFilterAllEq(cond.getTemperature(), cond.getWeather(), cond.getSensitivity()),
                        locationEq(cond.getCity(), cond.getDistrict()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.createdAt))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .where(postFilterAllEq(cond.getTemperature(), cond.getWeather(), cond.getSensitivity()),
                        locationEq(cond.getCity(), cond.getDistrict()));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Post> findPopularPosts(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        List<Post> posts = queryFactory.selectFrom(post)
                .where(post.createdAt.between(startDate, endDate))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.hearts.size()),
                        new OrderSpecifier<>(Order.DESC, post.views.size()),
                        new OrderSpecifier<>(Order.ASC, post.createdAt))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .where(post.createdAt.goe(startDate), post.createdAt.loe(endDate));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    /**
     *
     private Long id;
     private String img;
     private String description;
     private String author;
     private String profileImg;
     private String temperature;
     private String weather;
     private String sensitivity;
     private String city;
     private String district;
     private int heartCount;
     private boolean isHeart;
     private boolean isBookmark;
     private List<String> tags;
     private List<ProductLinkResponse> productLinks;
     * @param postId
     * @param userId
     * @return
     */
    @Override
    public PostDetailResponse findPostDetail1(Long postId, Long userId) {
        List<Heart> userHeart = queryFactory.selectFrom(heart)
                .where(heart.user.id.eq(userId))
                .fetch();
        boolean isHeart = !userHeart.isEmpty();

        List<Bookmark> userBookmark = queryFactory.selectFrom(bookmark)
                .where(bookmark.user.id.eq(userId))
                .fetch();
        boolean isBookmark = !userBookmark.isEmpty();
        return null;
//        return queryFactory
//                .selectFrom(post)
//                .leftJoin(post.hearts, heart)
//                .leftJoin(post.bookmarks, bookmark)
//                .leftJoin(post.postTags, postTag)
//                .leftJoin(postTag.tag, tag)
//                .leftJoin(post.productLinks, productLink)
//                .where(post.id.eq(postId))
//                .fetchJoin()
//                .fetchOne();
    }

    private List<ProductLinkResponse> getProductLinkResponse(QProductLink productLink) {
        return null;
    }

    @Override
    public Post findPostDetail(Long postId) {
        return queryFactory.selectFrom(post)
                .join(post.hearts, heart)
                .join(post.bookmarks, bookmark)
                .join(post.postTags, postTag)
                .join(postTag.tag, tag)
                .join(post.productLinks, productLink)
                .fetchJoin()
                .where(post.id.eq(postId))
                .fetchOne();
    }

    @Override
    public Page<PostThumbnailResponse> findMyPosts(Long userId, Pageable pageable) {
        List<PostThumbnailResponse> posts = queryFactory
                .select(Projections.constructor(PostThumbnailResponse.class,
                        post.id,
                        post.img,
                        post.description,
                        post.user.nickname.nickname,
                        post.user.profileImg,
                        post.createdAt))
                .from(post)
                .join(post.user, user)
                .where(user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.createdAt))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .join(post.user, user)
                .where(user.id.eq(userId));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostThumbnailResponse> findMyBookmarkPosts(Long userId, Pageable pageable) {
        List<PostThumbnailResponse> posts = queryFactory
                .select(Projections.constructor(PostThumbnailResponse.class,
                        post.id,
                        post.img,
                        post.description,
                        post.user.nickname.nickname,
                        post.user.profileImg,
                        post.createdAt))
                .from(post)
                .join(post.user, user)
                .join(post.bookmarks, bookmark)
                .where(bookmark.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.createdAt))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .join(post.user, user)
                .join(post.bookmarks, bookmark)
                .where(user.id.eq(userId), bookmark.user.id.eq(userId));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostThumbnailResponse> findMyHeartPosts(Long userId, Pageable pageable) {
        List<PostThumbnailResponse> posts = queryFactory
                .select(Projections.constructor(PostThumbnailResponse.class,
                        post.id,
                        post.img,
                        post.description,
                        post.user.nickname.nickname,
                        post.user.profileImg,
                        post.createdAt))
                .from(post)
                .join(post.user, user)
                .join(post.hearts, heart)
                .where(heart.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(new OrderSpecifier<>(Order.DESC, post.createdAt))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(post.count())
                .from(post)
                .join(post.user, user)
                .join(post.hearts, heart)
                .where(user.id.eq(userId), heart.user.id.eq(userId));

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
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
