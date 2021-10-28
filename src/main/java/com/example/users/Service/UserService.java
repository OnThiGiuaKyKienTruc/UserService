package com.example.users.Service;

import com.example.users.Entity.User;
import com.example.users.Repository.UserRepository;
import com.example.users.VO.Order;
import com.example.users.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findById(userId).get();
        vo.setUser(user);
        Order order =
                restTemplate.getForObject("http://localhost:9001/order/"
                                + user.getOrderId(),
                        Order.class);

        vo.setOrder(order);

        return vo;
    }
}
