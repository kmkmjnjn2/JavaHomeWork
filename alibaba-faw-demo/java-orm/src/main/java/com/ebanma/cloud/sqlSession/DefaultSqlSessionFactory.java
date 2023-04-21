package com.ebanma.cloud.sqlSession;

import com.ebanma.cloud.executor.Executor;
import com.ebanma.cloud.executor.SimpleExecutor;
import com.ebanma.cloud.pojo.Configuration;

/**
 * @author WHY
 * @version $ Id: DefaultSqlSessionFactory, v 0.1 2023/03/21 9:26 kmkmj Exp $
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        //创建执行器对象
        Executor executor = new SimpleExecutor();
        //生产sqlSession对象
        SqlSession sqlSession = new DefaultSqlSession(configuration, executor);
        return sqlSession;
    }
}