package com.banyuan.service.user;

import com.banyuan.bean.user.AuthorBean;
import com.banyuan.exception.UserException;
import com.banyuan.utils.EncryptUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public interface UserService {
    void register(AuthorBean bean) throws Exception;

    void login(HttpServletRequest req, AuthorBean bean) throws UserException;
}
