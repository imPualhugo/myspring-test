package com.banyuan.service.user.impl;

import com.banyuan.bean.user.AuthorBean;
import com.banyuan.exception.UserException;
import com.banyuan.mapper.user.AuthorMapper;
import com.banyuan.service.user.UserService;
import com.banyuan.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private AuthorMapper authorDao;

    public void register(AuthorBean bean) {
        if (null == bean
                || null == bean.getUserName()
                || "".equals(bean.getUserName())
                || null == bean.getPassword()
                || "".equals(bean.getPassword())
                || null == bean.getRepeatPassword()
                || "".equals(bean.getRepeatPassword())
        ) {
            throw new UserException("信息不完整");
        }
        if (!bean.getPassword().equals(bean.getRepeatPassword())) {
            throw new UserException("两次密码不一致");
        }
        AuthorBean author = authorDao.getUserByName(bean);
        if (author != null) {
            throw new UserException("用户已存在");
        }
        //可以添加进数据库

        //随机生成昵称
        if (bean.getNickName() == null || "".equals(bean.getNickName())) {
            bean.setNickName("游客" + new Random().nextInt(Integer.MAX_VALUE));
        }
        //生成创建和修改时间
        bean.setCreateTime(System.currentTimeMillis());
        bean.setUpdateTime(System.currentTimeMillis());

        //密码加密
        bean.setPassword(EncryptUtils.encrypt(bean.getPassword()));

        int i = authorDao.insertAuthor(bean);
        if (i <= 0) {
            throw new UserException("注册失败,请联系管理员,提示信息:DATABASE_INTERNAL_FATAL");
        }
    }

    public void login(HttpServletRequest req, AuthorBean bean) throws UserException {
//        String real = (String) req.getSession().getAttribute("checkCode");
//        String checkCode = req.getParameter("imageCode");

//        if (!real.equalsIgnoreCase(checkCode)){
//            throw new UserException("验证码错误");
//        }
        AuthorBean loginBean = (AuthorBean) req.getSession().getAttribute("login");
        if (null != loginBean) {
            return;
        }

        if (null == bean
                || null == bean.getUserName()
                || "".equals(bean.getUserName())
                || null == bean.getPassword()
                || "".equals(bean.getPassword())
        ) {
            throw new UserException("信息不完整");
        }
        AuthorBean queryBean = authorDao.getUserByName(bean);

        System.out.println(authorDao);

        String encryptPassword = EncryptUtils.encrypt(bean.getPassword());


        if (queryBean == null || !queryBean.getPassword().equals(encryptPassword)) {
            throw new UserException("账号密码错误");
        }


        req.getSession().setAttribute("login", queryBean);
    }
}
