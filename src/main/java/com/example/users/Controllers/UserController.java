package com.example.users.Controllers;

import com.example.users.Entity.Token;
import com.example.users.Entity.User;
import com.example.users.Service.TokenService;
import com.example.users.Service.UserService;
import com.example.users.VO.ResponseTemplateVO;
import com.example.users.authen.UserPrincipal;
import com.example.users.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@EnableEurekaClient

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

//    @PostMapping
//    public User saveUser(@RequestBody User user){
//
//        return userService.saveUser(user);
//    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

//    @GetMapping("/{id}")
//    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id")
//                                                            Long userId){
//        return userService.getUserWithDepartment(userId);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }

}
