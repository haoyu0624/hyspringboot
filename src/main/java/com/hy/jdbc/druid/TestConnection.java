package com.hy.jdbc.druid;/**
 * Created by haoy on 2018/1/3.
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @auther haoy
 * @create 2018/1/3
 */
public class TestConnection {

    //@Test
    public void mysqlTest()throws SQLException{
        DruidDataSource datasource = new DruidDataSource();
        String connectionUrl = "jdbc:mysql://10.129.56.190:3306/triggerdata?user=triggerdata&password=triggerdata&zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8";
        String driverClassName = "com.mysql.jdbc.Driver";

        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(connectionUrl);
        datasource.setInitialSize(0);
        datasource.setConnectionErrorRetryAttempts(3);
        datasource.setBreakAfterAcquireFailure(true);
        datasource.setFailFast(true);
        datasource.init();

        DruidPooledConnection connection = datasource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + "==================" + name);
        }
    }

    @Test
    public void oracleTest()throws SQLException{
        DruidDataSource datasource = new DruidDataSource();

        String connectionUrl = "jdbc:oracle:thin:testuser/testuser@127.0.0.1:1521:ORCL";
//        String driverClassName = "oracle.jdbc.driver.OracleDriver";

//        datasource.setUsername("testuser");
//        datasource.setPassword("testuser");

//        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(connectionUrl);
        datasource.setInitialSize(0);
        datasource.setConnectionErrorRetryAttempts(3);
        datasource.setBreakAfterAcquireFailure(true);
        datasource.setFailFast(true);
        datasource.init();

        DruidPooledConnection connection = datasource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from USERNB";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + "==================" + name);
//            String TABLE_NAME = resultSet.getString("TABLE_NAME");
//            System.out.println(TABLE_NAME + "==================" + TABLE_NAME);
        }
    }
}
