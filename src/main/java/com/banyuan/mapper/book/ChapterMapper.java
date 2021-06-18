package com.banyuan.mapper.book;

import com.banyuan.bean.book.ChapterBean;
import com.banyuan.utils.DBUtils;
import com.banyuan.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface ChapterMapper {

    public List<ChapterBean> getList(ChapterBean bean);


    public ChapterBean getChapterById(ChapterBean bean);

    public int insertChapter(ChapterBean bean);

    public int updateChapter(ChapterBean bean);


    public int deleteChapter(ChapterBean bean);

}
