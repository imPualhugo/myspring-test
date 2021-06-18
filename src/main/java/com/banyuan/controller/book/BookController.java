package com.banyuan.controller.book;


import com.banyuan.bean.book.BookBean;
import com.banyuan.bean.user.AuthorBean;
import com.banyuan.exception.BookException;
import com.banyuan.message.ResponseData;
import com.banyuan.service.book.BookService;
import com.banyuan.service.book.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.banyuan.message.MessageData.success;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @DeleteMapping("/{id}")
    public ResponseData delete(HttpSession session,@PathVariable Integer id) throws Exception {
        BookBean bean = new BookBean();
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        bean.setId(id);
        bookService.deleteBook(bean);

        return success();
    }

    @GetMapping("/id/{id}")
    public ResponseData getById(HttpSession session, @PathVariable Integer id) throws BookException {
        BookBean bean = new BookBean();
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        bean.setId(id);
        return success(bookService.getById(bean));
    }


    @PostMapping("")
    public ResponseData insert(HttpSession session, BookBean bean) throws Exception {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        bookService.insertBook(bean);
        return success();
    }

    @PostMapping("/update")
    public ResponseData update(HttpSession session, BookBean bean) throws Exception {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        bookService.updateBook(bean);

        return success();
    }


    @GetMapping("/page/{pageNo}")
    public ResponseData get(HttpSession session,@PathVariable Integer pageNo,String name) throws Exception {
        BookBean bean = new BookBean();
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        bean.setPageNo(pageNo);
        bean.setName(name);
        return success(bookService.getList(bean));
    }



}
