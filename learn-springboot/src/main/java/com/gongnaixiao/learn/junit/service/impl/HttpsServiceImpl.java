package com.gongnaixiao.learn.junit.service.impl;

import com.gongnaixiao.learn.junit.service.HttpsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class HttpsServiceImpl implements HttpsService {
    @Autowired
    RestTemplate httpsRestTemplate;

    @Override
    public void call1() {
        //final String url = "https://www.baidu.com";
        //final String url = "http://10.225.9.71:8080/api/cows/recvmsg";
        final String url = "https://10.225.8.199:1443/recvmsg";
        //final String url = "https://10.225.8.199:1443/httpServer/recvmsg";
        //final String url = "http://10.225.9.71:8080/httpServer/recvmsg";

        HttpHeaders headers = new HttpHeaders();

        //MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        MediaType type = MediaType.parseMediaType("text/plain;charset=utf-8");
        headers.setContentType(type);
        headers.set("X-Syscode", "ELCS");
        //headers.set("X-Token", "");
        headers.setAccept(MediaType.parseMediaTypes(MediaType.ALL_VALUE));

        //String content = "{\"username\":\"102100099999_ecls\",\"password\":\"abcdefgh12345678\"}";
        String content = "xxxxxxxxxx";

        HttpEntity<String> entity = new HttpEntity<>(content, headers);
        log.info("1111");
        try {
            ResponseEntity<String> responseEntity = httpsRestTemplate.postForEntity(url, entity, String.class);
            log.info("ss " + responseEntity.getStatusCode().toString());
            log.info("statusCode ", + responseEntity.getStatusCodeValue());
            String ss = responseEntity.getBody();
            log.info(ss);
        } catch (Exception e) {
            log.info("err");
            log.info(e.getMessage());
            log.info(e.getStackTrace().toString());
        }
    }
    @Override
    public void call() {
        //final String url = "https://www.baidu.com";
        final String url = "https://19.194.209.41/cows/login";

        HttpHeaders headers = new HttpHeaders();

        //MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        MediaType type = MediaType.parseMediaType("text/plain;charset=utf-8");
        headers.setContentType(type);
        headers.set("X-Syscode", "ELCS");
        //headers.set("X-Token", "");
        headers.setAccept(MediaType.parseMediaTypes(MediaType.ALL_VALUE));

        String content = "{\"username\":\"318110000014\",\"password\":\"111111a\"}";

        HttpEntity<String> entity = new HttpEntity<>(content, headers);
        log.info("1111");
        try {
            String ss = httpsRestTemplate.postForEntity(url, entity, String.class).getBody();
            log.info(ss);
        } catch (Exception e) {
            log.info("err");
            log.info(e.getMessage());
            log.info(e.getStackTrace().toString());
        }

    }
}
