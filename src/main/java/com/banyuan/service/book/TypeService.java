package com.banyuan.service.book;

import com.banyuan.bean.book.TypeBean;
import com.banyuan.dao.book.TypeDao;
import com.banyuan.exception.TypeException;
import com.banyuan.message.PageData;
import com.banyuan.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TypeService {
    private TypeDao typeDao = new TypeDao();

    /**
     * 按页读取type表
     *
     * @param bean 包含页数的bean
     * @return 包含数据的PageData
     */
    public PageData getList(TypeBean bean) throws TypeException {
        if (null == bean) {
            throw new TypeException("信息不完整");
        }

        PageData pageData = new PageData();
        pageData.setPageNo(bean.getPageNo());
        pageData.setPageSize(bean.getPageSize());
        pageData.setCount(typeDao.getCount(bean));
        List<TypeBean> list = typeDao.getList(bean);
        if (null == list) {
            throw new TypeException("数据无效");
        }
        //格式化时间
        list.forEach(item -> {
            item.formatTime(TimeUtils.sdf);
        });

        pageData.setList(list);
        return pageData;
    }

    /**
     * 不分页获取所有的类型,但仅获取id和name
     * @return
     * @throws TypeException
     */
    public List<TypeBean> getAll() throws TypeException {

        List<TypeBean> list = typeDao.getListAll();
        if (null == list) {
            throw new TypeException("数据无效");
        }
        return list;
    }

    /**
     * 插入一个类型
     * @param bean
     * @throws TypeException
     */
    public void insertType(TypeBean bean) throws TypeException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())) {
            throw new TypeException("信息不完整");
        }
        bean.setCreateTime(System.currentTimeMillis());
        bean.setUpdateTime(System.currentTimeMillis());
        int i = typeDao.insertType(bean);
        if (i <= 0) {
            throw new TypeException("插入失败");
        }
    }

    /**
     * 更改一个类型
     * @param bean
     * @throws TypeException
     */
    public void updateType(TypeBean bean) throws TypeException {
        if (null == bean
                || null == bean.getName()
                || "".equals(bean.getName())
                || bean.getId() <= 0) {
            throw new TypeException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = typeDao.updateType(bean);
        if (i <= 0) {
            throw new TypeException("更新失败");
        }
    }

    /**
     * 删除一个类型
     * @param bean
     * @throws TypeException
     */
    public void deleteType(TypeBean bean) throws TypeException {
        if (null == bean || bean.getId() <= 0) {
            throw new TypeException("信息不完整");
        }
        bean.setUpdateTime(System.currentTimeMillis());
        int i = typeDao.deleteType(bean);
        if (i <= 0) {
            throw new TypeException("删除失败");
        }
    }

}
