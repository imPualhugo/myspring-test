package com.banyuan.dao.book;

import com.banyuan.bean.book.BookBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


@Repository
public class BookDao {


    public int getCount(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            String name = "'%" + bean.getName() + "%'";
            ps = cn.prepareStatement("select count(1) from book where name like " + name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return 0;
    }

    public BookBean getById(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("select * from book where id=?");
            ps.setInt(1, bean.getId());
            rs = ps.executeQuery();
            return DBUtils.resultToBean(rs,BookBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }


    public List<BookBean> getList(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            String name = "'%" + bean.getName() + "%'";
            ps = cn.prepareStatement("select *, t.name as typeName from book b left join type t on b.typeId = t.id where authorId = ? and b.name like " + name + " limit ?,?;");
            ps.setInt(1, bean.getAuthorId());
            ps.setInt(2, bean.getStart());
            ps.setInt(3, bean.getPageSize());
            rs = ps.executeQuery();
            return DBUtils.resultToBeanList(rs, BookBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }


    public int insertBook(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("insert into book values (default,?,?,?,?,?,?,?)");
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getAuthorId());
            ps.setInt(3, bean.getTypeId());
            ps.setString(4, bean.getIntroduce());
            ps.setString(5, bean.getImage());
            ps.setLong(6, bean.getCreateTime());
            ps.setLong(7, bean.getUpdateTime());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }

    public int updateBook(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement(
                    "update book set name=?,typeId=?,introduce=?,image=?,updateTime=? where id=? and authorId=?");
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getTypeId());
            ps.setString(3, bean.getIntroduce());
            ps.setString(4, bean.getImage());
            ps.setLong(5, bean.getUpdateTime());
            ps.setInt(6, bean.getId());
            ps.setInt(7, bean.getAuthorId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }


    public int deleteBook(BookBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("delete from book where id=?");
            ps.setInt(1, bean.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }
}
