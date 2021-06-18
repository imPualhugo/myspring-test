package com.banyuan.service.book;

import com.banyuan.bean.book.TypeBean;
import com.banyuan.exception.TypeException;
import com.banyuan.message.PageData;
import com.banyuan.utils.TimeUtils;

import java.util.List;

public interface TypeService {
    /**
     * 按页读取type表
     *
     * @param bean 包含页数的bean
     * @return 包含数据的PageData
     */
    public PageData getList(TypeBean bean) throws TypeException;

    /**
     * 不分页获取所有的类型,但仅获取id和name
     *
     * @return
     * @throws TypeException
     */
    public List<TypeBean> getAll() throws TypeException;

    /**
     * 插入一个类型
     *
     * @param bean
     * @throws TypeException
     */
    public void insertType(TypeBean bean) throws TypeException;

    /**
     * 更改一个类型
     *
     * @param bean
     * @throws TypeException
     */
    public void updateType(TypeBean bean) throws TypeException;

    /**
     * 删除一个类型
     *
     * @param bean
     * @throws TypeException
     */
    public void deleteType(TypeBean bean) throws TypeException;

}
