package com.banyuan.service.book;

import com.banyuan.bean.book.ChapterBean;
import com.banyuan.dao.book.ChapterDao;
import com.banyuan.exception.ChapterException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChapterService {
    private ChapterDao chapterDao = new ChapterDao();


    /**
     * 不分页获取所有的类型,但仅获取id和name
     *
     * @return
     * @throws ChapterException
     */
    public List<ChapterBean> getAll(ChapterBean bean) throws ChapterException {

        List<ChapterBean> list = chapterDao.getList(bean);
        if (null == list) {
            throw new ChapterException("数据无效");
        }
        return list;
    }

    public ChapterBean getChapterById(ChapterBean bean) throws ChapterException {
        if (bean == null || bean.getId() <= 0 || bean.getAuthorId() <= 0) {
            throw new ChapterException("查询失败");
        }
        return chapterDao.getChapterById(bean);
    }

    /**
     * 插入一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    public void insertChapter(ChapterBean bean) throws ChapterException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())) {
            throw new ChapterException("信息不完整");
        }
        bean.setCreateTime(System.currentTimeMillis());
        bean.setUpdateTime(System.currentTimeMillis());
        int i = chapterDao.insertChapter(bean);
        if (i <= 0) {
            throw new ChapterException("插入失败");
        }
    }

    /**
     * 更改一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    public void updateChapter(ChapterBean bean) throws ChapterException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())
                || bean.getId() <= 0) {
            throw new ChapterException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = chapterDao.updateChapter(bean);
        if (i <= 0) {
            throw new ChapterException("更新失败");
        }
    }

    /**
     * 删除一个类型
     *
     * @param bean
     * @throws ChapterException
     */
    public void deleteChapter(ChapterBean bean) throws ChapterException {
        if (null == bean || bean.getId() <= 0) {
            throw new ChapterException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = chapterDao.deleteChapter(bean);
        if (i <= 0) {
            throw new ChapterException("删除失败");
        }
    }

}
