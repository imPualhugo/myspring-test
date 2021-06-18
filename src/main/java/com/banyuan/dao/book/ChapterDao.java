package com.banyuan.dao.book;

import com.banyuan.bean.book.ChapterBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


@Repository
@Deprecated
public class ChapterDao {


    public List<ChapterBean> getList(ChapterBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("select id,name,bookId from chapter where bookId=? and authorId=?");
            ps.setInt(1, bean.getBookId());
            ps.setInt(2, bean.getAuthorId());
            rs = ps.executeQuery();
            return DBUtils.resultToBeanList(rs, ChapterBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps, rs);
        }
        return null;
    }


    public ChapterBean getChapterById(ChapterBean bean){
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("select * from chapter where id=? and authorId = ?");
            ps.setInt(1,bean.getId());
            ps.setInt(2,bean.getAuthorId());
            rs = ps.executeQuery();
            return DBUtils.resultToBean(rs,ChapterBean.class);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtils.free(cn,ps,rs);
        }
        return null;
    }

    public int insertChapter(ChapterBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("insert into chapter values (default,?,?,?,?,?,?)");
            ps.setString(1, bean.getName());
            ps.setInt(2, bean.getAuthorId());
            ps.setInt(3, bean.getBookId());
            ps.setString(4, bean.getContent());
            ps.setLong(5, bean.getCreateTime());
            ps.setLong(6, bean.getUpdateTime());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }

    public int updateChapter(ChapterBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("update chapter set name=?,content=?,updateTime=? where id=? and authorId = ?");
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getContent());
            ps.setLong(3, bean.getUpdateTime());
            ps.setInt(4, bean.getId());
            ps.setInt(5, bean.getAuthorId());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.free(cn, ps);
        }
        return 0;
    }


    public int deleteChapter(ChapterBean bean) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JDBCUtils.getConnection();
            ps = cn.prepareStatement("delete from chapter where id=?");
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
