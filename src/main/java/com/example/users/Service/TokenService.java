package com.example.users.Service;

import com.example.users.Entity.Token;
import com.example.users.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }
}

