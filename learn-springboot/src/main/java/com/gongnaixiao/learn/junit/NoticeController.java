package com.gongnaixiao.learn.junit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NoticeController {
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public void call(@RequestBody String msg) {
        log.info("收到的消息：{}", msg);
    }
}
