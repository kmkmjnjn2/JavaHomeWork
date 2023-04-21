package com.ebanma.cloud.sqlSession;

import com.ebanma.cloud.config.XMLConfigBuilder;
import com.ebanma.cloud.pojo.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author WHY
 * @version $ Id: SqlSessionFactoryBuilder, v 0.1 2023/03/21 8:24 kmkmj Exp $
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream resourceAsStream) throws Exception {
        //1.配置解析文件，封装容器对象 XMLConfigBuilder:专门解析核心配置文件的解析类
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(resourceAsStream);
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }
}