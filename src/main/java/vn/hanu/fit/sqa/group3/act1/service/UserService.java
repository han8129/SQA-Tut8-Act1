package vn.hanu.fit.sqa.group3.act1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hanu.fit.sqa.group3.act1.model.Token;
import vn.hanu.fit.sqa.group3.act1.model.User;
import vn.hanu.fit.sqa.group3.act1.model.UserDto;
import vn.hanu.fit.sqa.group3.act1.repository.TokenRepository;
import vn.hanu.fit.sqa.group3.act1.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    public User save(UserDto userDto) {
        User user = new User(
            userDto.getUsername()
            , userDto.getPassword()
            , userDto.getEmail()
        );

        return userRepository.save(user);
    };

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No User with username %s found", username));
        }

        User temp = user.get();
        return new org.springframework.security.core.userdetails.User(temp.getEmail(), temp.getPassword(), temp.getAuthorities());
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
            .findByEmail(user.getEmail())
            .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = passwordEncoder
            .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String str = UUID.randomUUID().toString();

        Token token = new Token(
            user, str
        );

        tokenService.save( token );

        emailService.send(user.getUsername(), user.getEmail());

        return str;
    }

    public int enableByEmail(String email) {
        return userRepository.enableByEmail(email);
    }

    @Autowired private UserRepository userRepository;
    @Autowired BCryptPasswordEncoder passwordEncoder;
    @Autowired TokenService tokenService;
    @Autowired private TokenRepository tokenRepository;
    @Autowired private EmailService emailService;
}
