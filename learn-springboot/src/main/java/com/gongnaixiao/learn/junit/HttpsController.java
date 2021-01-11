package com.gongnaixiao.learn.junit;

import com.gongnaixiao.learn.junit.service.HttpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpsController {
    @Autowired
    HttpsService httpsService;

    @RequestMapping(value = "/xxxx", method = {RequestMethod.GET, RequestMethod.POST})
    public String call() {
        httpsService.call();

        return "hello";
    }
}
