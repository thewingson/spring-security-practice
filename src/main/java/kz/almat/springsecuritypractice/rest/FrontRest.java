package kz.almat.springsecuritypractice.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Almat on 04.06.2020
 */

@Controller
@RequestMapping("/")
public class FrontRest {

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("tweets")
    public String getCourses() {
        return "tweets";
    }
}
