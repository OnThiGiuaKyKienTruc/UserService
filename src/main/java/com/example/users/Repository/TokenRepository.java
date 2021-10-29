package com.example.users.Repository;

import com.example.users.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository
        extends JpaRepository<Token, Long> {

}
