package kz.almat.springsecuritypractice.config.security.role;

import com.google.common.collect.Sets;
import kz.almat.springsecuritypractice.config.security.permission.TweetPermission;

import java.util.Set;

import static kz.almat.springsecuritypractice.config.security.permission.TweetPermission.*;

/**
 * @author Almat on 04.06.2020
 */
public enum Role {
    USER(Sets.newHashSet(TWEET_READ)),
    ADMIN(Sets.newHashSet(TWEET_READ, TWEET_WRITE, TWEET_UPDATE, TWEET_DELETE));

    private final Set<TweetPermission> permissions;

    Role(Set<TweetPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<TweetPermission> getPermissions() {
        return permissions;
    }
}
