package com.example.users.VO;

import com.example.users.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private User user;
    private Order order;
}
