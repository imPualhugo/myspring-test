package com.banyuan.bean;

public class BaseBean {

    private int pageNo = 1;
    private int pageSize = 10;
    private int start = 0;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.start = (pageNo - 1) * pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 1;
        }
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        if (start < 0) {
            start = 0;
        }
        this.start = start;
    }


}
