package com.ebanma.cloud.sqlSession;

/**
 * @author WHY
 * @version $ Id: SqlSessionFactory, v 0.1 2023/03/21 8:29 kmkmj Exp $
 */
public interface SqlSessionFactory {

    //1.生产sqlSession对象 2.创建执行器对象
    SqlSession openSession();
}