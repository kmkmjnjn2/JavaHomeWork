package com.ebanma.cloud.usertestall.mapper;

import com.ebanma.cloud.usertestall.domain.entity.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

/**
 * @author WHY
 * @version $ Id: UserMapperTest, v 0.1 2023/03/21 20:43 kmkmj Exp $
 */
@SpringBootTest
public class UserMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
    @Autowired
    private UserMapper userMapper;

//    @Test
    public void find(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", "username1");
        List<UserDO> users = userMapper.selectByMap(hashMap);
        System.out.println(users);
        if (logger.isInfoEnabled()) {
            logger.info("{}",users);
        }

    }

}