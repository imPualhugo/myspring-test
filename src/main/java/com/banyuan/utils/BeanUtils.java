package com.banyuan.utils;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 转换Bean的工具,使用反射实现,可能会降低性能
 *
 * @author hyz
 */
public class BeanUtils {
    private BeanUtils() {
    }


    /**
     * 将rs中的数据转为一个clazz的bean对象
     * bean中属性必须与数据库中字段一一对应(顺序可不同)
     * ResultSet如果有多行数据将获取rs的下一行的数据(如果存在),并将rs指针向下移动一行
     *
     * @param clazz 转换成bean的类型
     * @param rs    ResultSet结果集 - 指针必须指向0
     * @param <T>  转换成bean的类型
     * @return Object类型的bean, 可随后强转类型
     */

    public static <T> T resultToBean(Class<T> clazz, ResultSet rs) {
        T t = null;
        try {
            if (rs.next()) {

                //通过反射创建一个对象
                t = clazz.getConstructor().newInstance();
                ResultSetMetaData meta = rs.getMetaData();
                int count = meta.getColumnCount();

                for (int i = 1; i <= count; i++) {
                    String label = meta.getColumnLabel(i);

                    Object obj = rs.getObject(label);

                    Field field = clazz.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t, obj);
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将ResultSet中多行转化为多个bean,其中ResultSet必须设置为TYPE_SCROLL_SENSITIVE
     * 即指针可回溯的
     * @param clazz 转化的bean的类的对象
     * @param rs 结果集
     * @param <T> bean类型
     * @return 使用ArrayList类型装着的bean容器
     */
    public static <T> ArrayList<T> resultsToBean(Class<T> clazz, ResultSet rs) {
        int i = 0;
        //获取总行数
        try {
            rs.last();
            i = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ArrayList<T> arrayList = new ArrayList<>(i);
        for (int j = 0; j < i; j++) {
            arrayList.add(resultToBean(clazz, rs));
        }
        return arrayList;
    }


    /**
     * 将requst中的数据转为一个clazz的bean对象
     *
     * @param clazz 转换成bean的类型
     * @param req   传入的request对象
     * @return Object类型的bean, 可随后强转类型
     */
    @Deprecated
    public static Object requestToBean(Class clazz, ServletRequest req) {
        Field[] fields = clazz.getDeclaredFields();

        try {
            Object obj = null;

            if (null != req) {
                obj = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    //将字段设为可控制
                    field.setAccessible(true);
                    field.set(obj, req.getParameter(field.getName()));
                }
            }
            return obj;
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
