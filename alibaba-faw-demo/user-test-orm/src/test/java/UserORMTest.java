
import com.ebanma.cloud.dao.IUserDao;
import com.ebanma.cloud.domain.User;
import com.ebanma.cloud.io.Resources;
import com.ebanma.cloud.sqlSession.SqlSession;
import com.ebanma.cloud.sqlSession.SqlSessionFactory;
import com.ebanma.cloud.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: UserORMTest, v 0.1 2023/03/20 21:14 kmkmj Exp $
 */

public class UserORMTest {

    @Test
    public void test1() throws Exception {
        //1.根据配置文件的路径，加载成字节输入流，存到内存中。注意：配置文件还未解析
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.解析了配置文件，封装了Configuration对象 2.创建了sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.创建了sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.调用sqlSession方法
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        User user2= sqlSession.selectOne("user.findByCondition", user);
        System.out.println(user2);

//        List<User> list = sqlSession.selectList("user.findAll", null);
//        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void test2() throws Exception {
        //1.根据配置文件的路径，加载成字节输入流，存到内存中。注意：配置文件还未解析
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.解析了配置文件，封装了Configuration对象 2.创建了sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.创建了sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

//        List<User> list = userDao.findAll();
//        System.out.println(list);

        //4.调用sqlSession方法
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        User user2= userDao.findByCondition(user);
        System.out.println(user2);

        sqlSession.close();
    }


}