package com.example.weeklyperiodical.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@EqualsAndHashCode
@ToString(callSuper = true)
public class UsersDetails extends User {
    /**
     * 管理员ID
     */
    private Long uid;


    public UsersDetails(Long uid, String username, String password,
                        boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled,
                true, true, true, authorities);
        this.uid=uid;
    }
}
