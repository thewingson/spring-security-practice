package kz.almat.springsecuritypractice.config.security.permission;

/**
 * @author Almat on 04.06.2020
 */
public enum TweetPermission {

    TWEET_READ("tweet:read"),
    TWEET_WRITE("tweet:write"),
    TWEET_UPDATE("tweet:update"),
    TWEET_DELETE("tweet:delete");

    private final String permission;

    TweetPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
