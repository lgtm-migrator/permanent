package com.qianxunclub.permanent;

import com.qianxunclub.permanent.service.oauth.QqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PermanentApplication.class)
public class MainTests {

    @Autowired
    private QqService qqService;

    @Test
    public void get() {
        System.out.println(qqService.authorizeUrl("aa"));
    }
}
