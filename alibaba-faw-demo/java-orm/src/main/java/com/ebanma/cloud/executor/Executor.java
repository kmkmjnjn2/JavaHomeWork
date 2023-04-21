package com.ebanma.cloud.executor;

import com.ebanma.cloud.pojo.Configuration;
import com.ebanma.cloud.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author kmkmj
 * @date 2023/03/21
 */
public interface Executor {
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object param) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException;


    void close();
}
