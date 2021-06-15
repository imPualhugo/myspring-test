package com.banyuan.bean.book;

import com.banyuan.bean.BaseBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class TypeBean extends BaseBean {
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name = "";

    private long createTime;
    private long updateTime;

    private String stringCreateTime;
    private String stringUpdateTime;

    public void formatTime(SimpleDateFormat sdf) {
        stringCreateTime = sdf.format(createTime);
        stringUpdateTime = sdf.format(updateTime);
    }



    public TypeBean() {
        super();
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

    public String getStringCreateTime() {
        return stringCreateTime;
    }

    public void setStringCreateTime(String stringCreateTime) {
        this.stringCreateTime = stringCreateTime;
    }

    public String getStringUpdateTime() {
        return stringUpdateTime;
    }

    public void setStringUpdateTime(String stringUpdateTime) {
        this.stringUpdateTime = stringUpdateTime;
    }
}
