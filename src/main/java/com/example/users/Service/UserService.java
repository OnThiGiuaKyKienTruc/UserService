package com.example.users.Service;

import com.example.users.Entity.User;
import com.example.users.Repository.UserRepository;
import com.example.users.VO.Order;
import com.example.users.VO.ResponseTemplateVO;
import com.example.users.authen.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    public UserPrincipal findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();

        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles())

                user.getRoles().forEach(r -> {
                    authorities.add(r.getRoleKey());
                    r.getPermissions().forEach(
                            p -> authorities.add(p.getPermissionKey()));
                });

            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }

        return userPrincipal;

    }
}
