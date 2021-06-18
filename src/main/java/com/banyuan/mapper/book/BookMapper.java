package com.banyuan.mapper.book;

import com.banyuan.bean.book.BookBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface BookMapper {

    public int getCount(BookBean bean);

    public BookBean getById(BookBean bean);


    public List<BookBean> getList(BookBean bean);


    public int insertBook(BookBean bean);

    public int updateBook(BookBean bean);


    public int deleteBook(BookBean bean);

}
