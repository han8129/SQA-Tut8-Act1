package vn.hanu.fit.sqa.group3.act1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = "/") public String index() {
        return "login";
    }
}
