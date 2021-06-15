package com.banyuan.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL相关的工具,包括建立数据库连接池清理释放Connection/Statement以及ResultSet
 *
 * @author hyz
 */

public class JDBCUtils {
    private JDBCUtils() {
    }


    private static final String DATABASE = "bqg";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static final DruidDataSource druidDataSource = new DruidDataSource();

    static {
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl(URL);
        druidDataSource.setUsername(USER);
        druidDataSource.setPassword(PASSWORD);
    }

    /**
     * 从线程池中获取一个数据库连接
     * @return 数据库连接
     * @throws SQLException 可能产生的SQL相关异常
     */
    public static Connection getConnection() throws SQLException {
        Connection cn = null;
        cn = druidDataSource.getConnection();

        return cn;
    }


    /**
     * 用于释放Connection/Statement和ResultSet的工具,可以接受null
     *
     * @param cn   需要释放的Connection
     * @param stat 需要释放的Statement
     * @param rs   需要释放的ResultSet
     */
    public static void free(Connection cn, Statement stat, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (null != stat) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (null != cn) {
            try {
                cn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 用于释放Connection和Statement的工具,可以接受null
     *
     * @param cn   需要释放的Connection
     * @param stat 需要释放的Statement
     */
    public static void free(Connection cn, Statement stat) {
        free(cn, stat, null);
    }

}
