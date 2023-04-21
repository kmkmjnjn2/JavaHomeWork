package com.ebanma.cloud.mapper;
import com.ebanma.cloud.domain.UserDO;
import org.junit.Test;

import java.sql.*;

/**
 * @author WHY
 * @version $ Id: UserJDBCest, v 0.1 2023/03/15 9:30 kmkmj Exp $
 */
public class UserJDBCest {

    /**
     * 1.加载数据库驱动
     * 2.通过驱动管理类获取数据库链接
     * 3.定义sql语句?表示占位符
     * 4.获取预处理statement
     * 5.设置参数，第一个参数为sql语句中参数的序号(从1开始),第二个参数为设置的参数值
     * 6.向数据库发出sql执行查询，查询出结果集
     * 7.遍历查询结果集
     * 8.并封装返回对象
     * */

    @Test
    public void selectOne(){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement=null;
        Connection connection = null;
        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/alibaba-faw-demo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8", "root", "mysql");
            //3.定义sql语句
            String sql = "select * from tb_user where username=?";
            //4.获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //5.设置参数
            preparedStatement.setString(1, "username5");
            //6.向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //7.遍历查询结果集
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                //8.并封装返回对象
                UserDO user = new UserDO();
                user.setId(id);
                user.setUsername(username);
                System.out.println(user);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

}