package com.banyuan.controller.user;

import com.banyuan.bean.user.AuthorBean;
import com.banyuan.message.MessageData;
import com.banyuan.message.ResponseData;
import com.banyuan.service.user.UserService;
import com.banyuan.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseData register(AuthorBean bean) throws Exception {
        userService.register(bean);
        ResponseData rd = new ResponseData();
        rd.setState(MessageData.REQUEST_SUCCESS.getCode());
        rd.setMessage(MessageData.REQUEST_SUCCESS.getMessage());
        return rd;
    }

    @PostMapping("/login")
    public ResponseData login(AuthorBean bean, HttpServletRequest req) throws Exception {
        System.out.println(bean);
        userService.login(req, bean);
        ResponseData rd = new ResponseData();
        rd.setState(MessageData.REQUEST_SUCCESS.getCode());
        rd.setMessage(MessageData.REQUEST_SUCCESS.getMessage());
        return rd;
    }

}
