package com.ebanma.cloud;

import com.ebanma.cloud.bean.SimpleBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WHY
 * @version $ Id: com.ebanma.cloud.TestStarter, v 0.1 2023/04/14 9:46 kmkmj Exp $
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStarter {

    @Autowired
    private SimpleBean simpleBean;

    @Test
    public void myStarterTest(){
        System.out.println(simpleBean);
    }

}