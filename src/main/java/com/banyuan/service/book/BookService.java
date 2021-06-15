package com.banyuan.service.book;

import com.banyuan.bean.book.BookBean;
import com.banyuan.dao.book.BookDao;
import com.banyuan.exception.BookException;
import com.banyuan.message.PageData;
import com.banyuan.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private BookDao bookDao = new BookDao();

    /**
     * 按页读取book表
     *
     * @param bean 包含页数的bean
     * @return 包含数据的PageData
     */
    public PageData getList(BookBean bean) throws BookException {
        if (null == bean) {
            throw new BookException("信息不完整");
        }

        PageData pageData = new PageData();
        pageData.setPageNo(bean.getPageNo());
        pageData.setPageSize(bean.getPageSize());
        pageData.setCount(bookDao.getCount(bean));
        List<BookBean> list = bookDao.getList(bean);
        if (null == list) {
            pageData.setList(new ArrayList<BookBean>());
            return pageData;
        }
        //格式化时间
        list.forEach(item -> {
            item.formatTime(TimeUtils.sdf);
        });

        pageData.setList(list);
        return pageData;
    }

    /**
     * 通过id获取某个book的完整信息
     *
     * @param bean
     * @return
     */
    public BookBean getById(BookBean bean) throws BookException {
        if (null == bean || bean.getId() <= 0) {
            throw new BookException("信息有误");
        }
        BookBean bookBean = bookDao.getById(bean);
        bookBean.formatTime(TimeUtils.sdf);
        return bookBean;
    }

    /**
     * 不分页获取所有的类型,但仅获取id和name
     *
     * @return
     * @throws BookException
     */
    public List<BookBean> getListAll() throws BookException {

        List<BookBean> list = null;
        if (null == list) {
            throw new BookException("数据无效");
        }
        return list;
    }

    public void insertBook(BookBean bean) throws BookException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())) {
            throw new BookException("信息不完整");
        }
        bean.setCreateTime(System.currentTimeMillis());
        bean.setUpdateTime(System.currentTimeMillis());
        int i = bookDao.insertBook(bean);
        if (i <= 0) {
            throw new BookException("插入失败");
        }
    }

    public void updateBook(BookBean bean) throws BookException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())
                || bean.getId() <= 0) {
            throw new BookException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = bookDao.updateBook(bean);
        if (i <= 0) {
            throw new BookException("更新失败");
        }
    }

    public void deleteBook(BookBean bean) throws BookException {
        if (null == bean || bean.getId() <= 0) {
            throw new BookException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = bookDao.deleteBook(bean);
        if (i <= 0) {
            throw new BookException("删除失败");
        }
    }

}
