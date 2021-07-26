package com.banyuan.dao.book;

import com.banyuan.bean.book.TypeBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/*
  已弃用,请使用mapper中的映射类
  This class is deprecated, use package 'mapper' classes please.
 */
@Repository
@Deprecated
public class TypeDao {


    public int getCount(TypeBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            String name = "'%" + bean.getName() + "%'";
            ps = cn.prepareStatement("select count(1) from type where name like " + name);
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

    public List<TypeBean> getList(TypeBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            String name = "'%" + bean.getName() + "%'";
            ps = cn.prepareStatement("select * from type where name like " + name + " limit ?,?");
            ps.setInt(1, bean.getStart());
            ps.setInt(2, bean.getPageSize());
            rs = ps.executeQuery();
            return DBUtils.resultToBeanList(rs, TypeBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }

    public List<TypeBean> getListAll() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("select id,name from type");

            rs = ps.executeQuery();
            return DBUtils.resultToBeanList(rs, TypeBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }

    public int insertType(TypeBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("insert into type values (default,?,?,?)");
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getCreateTime());
            ps.setLong(3, bean.getUpdateTime());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }

    public int updateType(TypeBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("update type set name=?,updateTime=? where id=?");
            ps.setString(1, bean.getName());
            ps.setLong(2, bean.getUpdateTime());
            ps.setInt(3, bean.getId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }


    public int deleteType(TypeBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("delete from type where id=?");
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
