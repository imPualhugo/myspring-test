package com.banyuan.service.book;

import com.banyuan.bean.book.ChapterBean;
import com.banyuan.exception.ChapterException;

import java.util.List;

public interface ChapterService {


    /**
     * 不分页获取所有的类型,但仅获取id和name
     *
     * @return
     * @throws ChapterException
     */
    List<ChapterBean> getAll(ChapterBean bean) throws ChapterException;

    ChapterBean getChapterById(ChapterBean bean) throws ChapterException;

    /**
     * 插入一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    void insertChapter(ChapterBean bean) throws ChapterException;

    /**
     * 更改一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    void updateChapter(ChapterBean bean) throws ChapterException;

    /**
     * 删除一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    void deleteChapter(ChapterBean bean) throws ChapterException;
}
