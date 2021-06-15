package com.banyuan.bean.book;

import com.banyuan.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
public class ChapterBean extends BaseBean {

    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name = "";
    private int authorId;
    private int bookId;
    private String content;
    private long createTime;
    private long updateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}
