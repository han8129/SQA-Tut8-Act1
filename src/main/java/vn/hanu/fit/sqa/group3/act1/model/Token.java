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
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
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

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private final String str;

    @ManyToOne private final User owner;
    private final LocalDateTime createdAt;
    private final LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    public Token(User owner, String str, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime confirmedAt) {
        this.owner = owner;
        this.str = str;
        this.confirmedAt = confirmedAt;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
