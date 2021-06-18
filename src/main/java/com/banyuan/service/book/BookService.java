package com.banyuan.service.book;

import com.banyuan.bean.book.BookBean;
import com.banyuan.exception.BookException;
import com.banyuan.message.PageData;

import java.util.List;

public interface BookService {

    PageData getList(BookBean bean) throws BookException;

    /**
     * 通过id获取某个book的完整信息
     *
     * @param bean
     * @return
     */
    BookBean getById(BookBean bean) throws BookException;

    /**
     * 不分页获取所有的类型,但仅获取id和name
     *
     * @return
     * @throws BookException
     */
    List<BookBean> getListAll() throws BookException;

    void insertBook(BookBean bean) throws BookException;

    void updateBook(BookBean bean) throws BookException;

    void deleteBook(BookBean bean) throws BookException;

}
