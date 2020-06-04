package kz.almat.springsecuritypractice.rest;

import kz.almat.springsecuritypractice.model.Tweet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Almat on 04.06.2020
 */

@RestController
@RequestMapping("/api/tweets")
@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN, ROLE_USER')")
public class TweetRest {

    private Set<Tweet> tweets = new HashSet<>();

    public TweetRest() {
        tweets.add(new Tweet(1, "aaa"));
        tweets.add(new Tweet(2, "sss"));
        tweets.add(new Tweet(3, "ddd"));
    }

    @GetMapping
    public Set<Tweet> all() {
        return tweets;
    }

    @GetMapping("{id}")
    public Tweet getById(@PathVariable Integer id) {
        Optional<Tweet> first = tweets.stream().filter(tweet -> tweet.getId().equals(id)).findFirst();
        if (first.isPresent()) {
            return first.get();
        } else {
            throw new RuntimeException("Tweet not found!");
        }
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('tweet:write')")
    public void add(@RequestBody Tweet tweet) {
        tweets.add(tweet);
    }

    @PutMapping("{id}")
    @PreAuthorize(value = "hasAuthority('tweet:update')")
    public void update(@PathVariable Integer id,
                       @RequestBody Tweet tweet) {
        tweets.stream()
                .filter(tweet1 -> tweet.getId().equals(id)).findFirst()
                .ifPresent(tweet1 -> tweet.setText(tweet.getText()));
    }

    @DeleteMapping("{id}")
    @PreAuthorize(value = "hasAuthority('tweet:delete')")
    public void delete(@PathVariable Integer id) {
        tweets.stream()
                .filter(tweet -> tweet.getId().equals(id)).findFirst()
                .ifPresent(tweet -> tweets.remove(tweet));
    }

}
