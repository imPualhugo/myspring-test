package com.banyuan.message;

/**
 * @author hyz
 */
public class PageData {
    private int pageNo;
    private int count;
    private int pageSize;
    private Object list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "pageNo=" + pageNo +
                ", count=" + count +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
