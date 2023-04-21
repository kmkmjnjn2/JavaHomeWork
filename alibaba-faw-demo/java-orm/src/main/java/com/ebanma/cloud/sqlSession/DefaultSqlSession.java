package com.ebanma.cloud.sqlSession;

import com.ebanma.cloud.executor.Executor;
import com.ebanma.cloud.pojo.Configuration;
import com.ebanma.cloud.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: DefaultSqlSession, v 0.1 2023/03/21 9:35 kmkmj Exp $
 */
public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;
    private Executor executor;
    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object param) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //将查询操作委派给底层的执行器
        //query():执行底层的JDBC 1.数据库配置信息，2.sql配置信息
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<E> list = executor.query(configuration, mappedStatement, param);
        return list;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<T> list = selectList(statementId, param);
        if(list.size()==1){
            return (T)list.get(0);
        }else if(list.size()>0){
            throw new RuntimeException("返回结果过多");
        }else{
            return null;
        }
    }

    @Override
    public void close() {
        executor.close();
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //基于JDK动态代理生成基于接口的代理对象
        Object proxy = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] objs) throws Throwable {
                //处理具体逻辑 ：执行底层的JDBC
                //通过调用sqlSession里面的方法来完成方法调用
                //参数的准备:1.statementId 2.param
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;

                MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
                String commandType = mappedStatement.getCommandType();
                switch (commandType) {
                    case "select":
                        Type genericReturnType = method.getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            if (objs != null) {
                                return selectList(statementId, objs[0]);
                            }
                            return selectList(statementId, null);
                        }
                        return selectOne(statementId, objs[0]);
                    case "insert":
                        return null;
                    case "update":
                        return null;
                    case "delete":
                        return null;
                    default:
                        throw new RuntimeException("不支持的sql类型");
                }
            }
        });
        return (T)proxy;
    }
}