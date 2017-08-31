package com.hy.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoy on 2017/7/17.
 */
public class MysqlJdbc {
    public static void main(String[] args) throws Exception {
        druidJdbc();
    }

    public static void druidJdbc() throws Exception {
        Map<String, String> columns = new HashMap<>();
        columns.put("createtime","createtime");
        columns.put("code","code");
        columns.put("flag","flag");
        columns.put("roleId","roleId");
        columns.put("sex","sex");
        columns.put("orgId","orgId");
        columns.put("deleted","deleted");
        columns.put("phone","phone");
        columns.put("name","name");
        columns.put("id","id");
        columns.put("age","age");
        columns.put("email","email");
        columns.put("desc","desc");
        columns.put("order","order");

        String url = "jdbc:mysql://10.129.56.190:3306/triggerdata?user=triggerdata&password=triggerdata&zeroDateTimeBehavior=convertToNull&amp;useUnicode=true&amp;characterEncoding=utf-8";
//        String url = "jdbc:mysql://10.129.56.190:3306/triggerdata?zeroDateTimeBehavior=convertToNull&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String name = "com.mysql.jdbc.Driver";
//        String user = "triggerdata";
//        String password = "triggerdata";
        String sql = "select `createtime` `createtime`, `code` `code`, `flag` `flag`, `modifytime` `modifytime`, " +
                "`roleId` `roleId`, `sex` `sex`, `orgId` `orgId`, `deleted` `deleted`, `phone` `phone`, `name` `name`, " +
                "`id` `id`, `age` `age`, `email` `email`, `desc` `desc`, `order` `order` from user";


        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(name);
        datasource.setUrl(url);
//        datasource.setUsername(user);
//        datasource.setPassword(password);
        datasource.setInitialSize(0);
        datasource.init();

        Connection conn = datasource.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);//准备执行语句
        ResultSet resultSet = pst.executeQuery();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while(resultSet.next()){
            Map<String, Object> row = new HashMap<String, Object>();
            for(String columnName : columns.keySet()){
                row.put(columnName, resultSet.getString(columnName));
//                row.put(columnName, resultSet.getObject(columnName));
            }
            row.put("test",123123);
            list.add(row);
        }
        System.out.println(list.toString());
    }


    public static void jdbc() throws Exception {
        Map<String, String> columns = new HashMap<>();
        columns.put("createtime","createtime");
        columns.put("code","code");
        columns.put("flag","flag");
        columns.put("roleId","roleId");
        columns.put("sex","sex");
        columns.put("orgId","orgId");
        columns.put("deleted","deleted");
        columns.put("phone","phone");
        columns.put("name","name");
        columns.put("id","id");
        columns.put("age","age");
        columns.put("email","email");
        columns.put("desc","desc");
        columns.put("order","order");

        String url = "jdbc:mysql://10.129.56.190:3306/triggerdata?user=triggerdata&password=triggerdata&zeroDateTimeBehavior=convertToNull&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String name = "com.mysql.jdbc.Driver";
        String user = "triggerdata";
        String password = "triggerdata";
        String sql = "select `createtime` `createtime`, `code` `code`, `flag` `flag`, `modifytime` `modifytime`, " +
                "`roleId` `roleId`, `sex` `sex`, `orgId` `orgId`, `deleted` `deleted`, `phone` `phone`, `name` `name`, " +
                "`id` `id`, `age` `age`, `email` `email`, `desc` `desc`, `order` `order` from user";
        Class.forName(name);//指定连接类型
        Connection conn = DriverManager.getConnection(url);//获取连接
        PreparedStatement pst = conn.prepareStatement(sql);//准备执行语句
        ResultSet resultSet = pst.executeQuery();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while(resultSet.next()){
            Map<String, Object> row = new HashMap<String, Object>();
            for(String columnName : columns.keySet()){
                row.put(columnName, resultSet.getString(columnName));
//                row.put(columnName, resultSet.getObject(columnName));
            }
            row.put("test",123123);
            list.add(row);
        }
        System.out.println(list.toString());
    }
}
