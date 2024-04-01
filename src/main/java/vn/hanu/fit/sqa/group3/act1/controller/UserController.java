package vn.hanu.fit.sqa.group3.act1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.hanu.fit.sqa.group3.act1.repository.UserRepository;
import vn.hanu.fit.sqa.group3.act1.service.UserService;

public class UserController {

    @PostMapping("/registration") public String registration(
        @ModelAttribute("user") User user, BindingResult binding
    ) {

    }
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
}
