package com.qianxunclub.permanent;

import com.qianxunclub.permanent.service.login.QqService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PermanentApplication.class)
public class MainTests {

    @Autowired
    private QqService qqService;

    @Test
    public void get() {
        System.out.println(qqService.authorizeUrl("aa"));

    }
}
