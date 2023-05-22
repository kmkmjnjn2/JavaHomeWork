package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.usertestall.domain.entity.gen.TbUser;
import com.ebanma.cloud.usertestall.domain.entity.gen.TbUserExample;
import com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper;
import com.ebanma.cloud.usertestall.util.DaoUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: MybatisTest, v 0.1 2023/03/15 13:47 kmkmj Exp $
 */
public class MybatisTest {

    /**
     * 传统方式
     */
    @Test

    public void test1() throws IOException {
        // 1. 读取配置文件，读成字节输入流，注意：现在还没解析
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");

        // 2. 解析配置文件，封装Configuration对象   创建DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 生产了DefaultSqlsession实例对象   设置了事务不自动提交  完成了executor对象的创建
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4.(1)根据statementid来从Configuration中map集合中获取到了指定的MappedStatement对象
        //   (2)将查询任务委派了executor执行器
        TbUser user = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey", 1L);
        System.out.println(user);
        TbUser user2 = sqlSession.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey", 1L);
        System.out.println(user2);

        // 5.释放资源
        sqlSession.close();
    }

    /**
     * mapper代理方式
     */
    @Test
    public void test2() throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        // 使用JDK动态代理对mapper接口产生代理对象
        TbUserMapper mapper = sqlSession.getMapper(TbUserMapper.class);

        //代理对象调用接口中的任意方法，执行的都是动态代理中的invoke方法
        List<TbUser> all = mapper.selectByExample(new TbUserExample());
        for (TbUser user : all) {
            System.out.println(user);
        }
    }

    @Test
    public void test3() throws IOException {
        TbUser user = DaoUtils.execute(session -> session.selectOne("com.ebanma.cloud.usertestall.mapper.gen.TbUserMapper.selectByPrimaryKey", 1L));
        System.out.println(user);

    }
}