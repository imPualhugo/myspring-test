package com.banyuan.mapper.user;

import com.banyuan.bean.user.AuthorBean;


public interface AuthorMapper {
    AuthorBean getUserByName(AuthorBean bean);

    int insertAuthor(AuthorBean bean);
}
