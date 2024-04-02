package vn.hanu.fit.sqa.group3.act1.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Token {
    public Token(User owner, String str) {
        this.owner = owner;
        this.str = str;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
        this.confirmed = false;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public User getOwner() {
        return owner;
    }

    public String getStr() {
        return str;
    }

    public Long getId() {
        return id;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private final String str;

    @ManyToOne private final User owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;
    private boolean confirmed;

    public Token(User owner, String str, LocalDateTime createdAt, LocalDateTime expiresAt, Boolean confirmed) {
        this.owner = owner;
        this.str = str;
        this.confirmed = confirmed;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
