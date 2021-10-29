package com.example.users.Controllers;

import com.example.users.Entity.User;
import com.example.users.Service.UserService;
import com.example.users.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@EnableEurekaClient
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${welcome}")
    String AA;

    @PostMapping
    public User saveUser(@RequestBody User user){

        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id")
                                                            Long userId){
        return userService.getUserWithDepartment(userId);
    }

    @GetMapping
    public String helloWorld(){
        return AA;
    }
}
