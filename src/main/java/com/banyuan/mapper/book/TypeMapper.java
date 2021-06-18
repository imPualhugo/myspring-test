package com.banyuan.mapper.book;

import com.banyuan.bean.book.TypeBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface TypeMapper {

    public int getCount(TypeBean bean);

    public List<TypeBean> getList(TypeBean bean);

    public List<TypeBean> getListAll();

    public int insertType(TypeBean bean);

    public int updateType(TypeBean bean);

    public int deleteType(TypeBean bean);
}
