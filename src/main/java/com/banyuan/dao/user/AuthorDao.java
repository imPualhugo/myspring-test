package com.banyuan.dao.user;


import com.banyuan.bean.user.AuthorBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AuthorDao {

    public AuthorBean getUserByName(AuthorBean bean) {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("select * from author where userName=?");
            ps.setString(1, bean.getUserName());
            rs = ps.executeQuery();

            AuthorBean authorBean = DBUtils.resultToBean(rs, AuthorBean.class);
            return authorBean;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }

    public int insertAuthor(AuthorBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        int i = 0;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("insert into author values (default,?,?,?,?,?)");
            ps.setString(1, bean.getUserName());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getNickName());
            ps.setLong(4, bean.getCreateTime());
            ps.setLong(5, bean.getUpdateTime());
            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return i;
    }
}
