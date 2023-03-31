package com.ootd.commute.domain.user;

import com.ootd.commute.domain.BaseEntity;
import com.ootd.commute.domain.heart.Heart;
import com.ootd.commute.domain.post.Post;
import com.ootd.commute.domain.view.View;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true)
    private String userId;

    private String name;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<View> views = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Heart> hearts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
