package com.gongnaixiao.learn.junit.service;

import com.gongnaixiao.learn.junit.JunitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JunitApplication.class})
public class HttpsServiceImplTest {

    @Autowired
    private HttpsService httpsService;

    @Test
    public void testAddUser() {
        httpsService.call1();
    }
}