package vn.hanu.fit.sqa.group3.act1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hanu.fit.sqa.group3.act1.model.Token;
import vn.hanu.fit.sqa.group3.act1.repository.TokenRepository;

import java.util.Optional;

@Service
public class TokenService {
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findByStr(String str) {
        return tokenRepository.findByStr(str);
    }

    public int confirm(String str) {
        return tokenRepository.confirm(str);
    }

    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Autowired private final TokenRepository tokenRepository;
}
