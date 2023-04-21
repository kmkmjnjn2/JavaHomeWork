package com.ebanma.cloud.executor;

import com.ebanma.cloud.config.BoundSql;
import com.ebanma.cloud.pojo.Configuration;
import com.ebanma.cloud.pojo.MappedStatement;
import com.ebanma.cloud.utils.GenericTokenParser;
import com.ebanma.cloud.utils.ParameterMapping;
import com.ebanma.cloud.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: SimpleExecutor, v 0.1 2023/03/21 9:37 kmkmj Exp $
 */
public class SimpleExecutor implements Executor{

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object param) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {

        //加载驱动，获取数据库连接
        connection = configuration.getDataSource().getConnection();
        //2.获取preparedStatement预编译对象
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        String finalSql = boundSql.getSql();
        preparedStatement = connection.prepareStatement(finalSql);

        //3.设置参数
        //获取类
        String parameterType = mappedStatement.getParameterType();
        if (parameterType != null) {
            Class<?> parameterTypeClass = Class.forName(parameterType);
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappingList.size(); i++) {
                //获取参数
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                String parameterName = parameterMapping.getContent();
                Field field = parameterTypeClass.getDeclaredField(parameterName);
                field.setAccessible(true);
                Object value = field.get(param);
                preparedStatement.setObject(i+1,value);
            }
        }

        //4.执行sql 发起查询
        resultSet = preparedStatement.executeQuery();
        ArrayList<E> list = new ArrayList<>();
        while(resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            String resultType = mappedStatement.getResultType();
            Class<?> resultTypeClass = Class.forName(resultType);
            Object o = resultTypeClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段值
                Object columnValue = resultSet.getObject(i);
                //属性描述器：通过API方法获取某个属性的读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeClass );
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, columnValue);
            }
            list.add((E) o);
        }

        return list;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!= null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet!= null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @author: WangHaiyang
     * @date: 2023/3/21 10:16
     * @param sql
     * @return BoundSql
     * @description:1.#{}占位符替换成? 2.解析替换的过程中，将#{}中的值保存下起来
     */
    private BoundSql getBoundSql(String sql) {
        //创建标记处理器
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        //创建标记解析器
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String finalSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(finalSql, parameterMappings);
        return boundSql;
    }



}