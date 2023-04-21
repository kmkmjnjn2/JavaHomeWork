package com.ebanma.cloud.sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kmkmj
 * @date 2023/03/21
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId,Object param) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    <T> T selectOne(String statementId, Object param) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;




    void close();

    <T> T getMapper(Class<?> mapperClass) ;



}
