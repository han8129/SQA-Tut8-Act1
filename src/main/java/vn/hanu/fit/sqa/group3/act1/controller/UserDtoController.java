package vn.hanu.fit.sqa.group3.act1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hanu.fit.sqa.group3.act1.model.Token;
import vn.hanu.fit.sqa.group3.act1.model.User;
import vn.hanu.fit.sqa.group3.act1.model.UserDto;
import vn.hanu.fit.sqa.group3.act1.repository.UserRepository;
import vn.hanu.fit.sqa.group3.act1.service.EmailService;
import vn.hanu.fit.sqa.group3.act1.service.TokenService;
import vn.hanu.fit.sqa.group3.act1.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(value = "/registration")
public class UserDtoController {
    @GetMapping("") public String registration( Model model ) {
        model.addAttribute("user", new UserDto());
        return "index";
    }

    @PostMapping("/process")
    public String process(
        @ModelAttribute("user") UserDto userDto
    ) {

        User user = new User(userDto);
        String token = userService.signUpUser(user);
        emailService.sendVerification( userDto.getEmail(), token);

        return "redirect:/registration";
    }

    @Transactional
    @GetMapping("/confirm") public String confirm(
        @RequestParam(value = "token", required = true) String str
    ) {
        Optional<Token> temp = tokenService.findByStr(str);

        if (temp.isEmpty()) { return "redirect:/registration"; }

        Token token = temp.get();
        LocalDateTime expiredAt = token.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Email expired");
        }

        if (false == token.getConfirmed()) {
            tokenService.confirm(str);
            userService.enableByEmail(
                token.getOwner().getEmail());
        }

        return "redirect:/login";
    }


    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private TokenService tokenService;
    @Autowired private EmailService emailService;
}
