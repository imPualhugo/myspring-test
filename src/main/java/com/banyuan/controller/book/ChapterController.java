package com.banyuan.controller.book;


import com.banyuan.bean.book.ChapterBean;
import com.banyuan.bean.user.AuthorBean;
import com.banyuan.exception.ChapterException;
import com.banyuan.message.ResponseData;
import com.banyuan.service.book.ChapterService;
import com.banyuan.service.book.impl.ChapterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.banyuan.message.MessageData.success;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @DeleteMapping("/{id}")
    public ResponseData delete(HttpSession session, @PathVariable Integer id) throws ChapterException {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        ChapterBean bean = new ChapterBean();
        bean.setAuthorId(author.getId());
        bean.setId(id);
        chapterService.deleteChapter(bean);
        return success();
    }

    @GetMapping("/id/{id}")
    public ResponseData getById(HttpSession session, @PathVariable Integer id) throws ChapterException {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        ChapterBean bean = new ChapterBean();
        bean.setAuthorId(author.getId());
        bean.setId(id);
        return success(chapterService.getChapterById(bean));
    }


    @PostMapping("")
    public ResponseData insert(HttpSession session, @RequestBody ChapterBean bean) throws ChapterException {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        chapterService.insertChapter(bean);
        return success();
    }

    @PutMapping("")
    public ResponseData update(HttpSession session, @RequestBody ChapterBean bean) throws Exception {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        bean.setAuthorId(author.getId());
        chapterService.updateChapter(bean);
        return success();
    }

    @GetMapping("/{bookId}")
    public ResponseData get(HttpSession session, @PathVariable Integer bookId) throws Exception {
        AuthorBean author = (AuthorBean) session.getAttribute("login");
        ChapterBean bean = new ChapterBean();
        bean.setAuthorId(author.getId());
        bean.setBookId(bookId);
        return success(chapterService.getAll(bean));
    }


}
