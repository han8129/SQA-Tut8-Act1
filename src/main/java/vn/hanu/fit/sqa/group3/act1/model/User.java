package vn.hanu.fit.sqa.group3.act1.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.hanu.fit.sqa.group3.act1.constant.UserRole;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = UserRole.USER;
        this.enabled = false;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());

        return Collections.singleton(authority);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

     public void setPassword(String password) {
        this.password = password;
     }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String username;
    private String password;
    private final String email;
    @Enumerated(EnumType.STRING)
    private final UserRole role;
    private Long id;
    private final Boolean enabled;
    @OneToMany(mappedBy = "owner") private List<Token> tokenList;

    public User(String username, String password, String email, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = UserRole.USER;
        this.enabled = enabled;
    }

}
