package com.banyuan.controller.user;

import com.banyuan.bean.user.AuthorBean;
import com.banyuan.message.MessageData;
import com.banyuan.message.ResponseData;
import com.banyuan.service.user.UserService;
import com.banyuan.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.banyuan.message.MessageData.success;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseData register(@RequestBody AuthorBean bean) throws Exception {
        userService.register(bean);
        return success();
    }

    @PostMapping("/login")
    public ResponseData login(@RequestBody AuthorBean bean, HttpServletRequest req) throws Exception {
        System.out.println(bean);

        userService.login(req, bean);

        return success();
    }

}
