package com.gongnaixiao.learn.junit.config;

import com.google.common.collect.Lists;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class HttpRestTemplateConfig {

    @Bean
    RestTemplate httpsRestTemplate (HttpComponentsClientHttpRequestFactory httpsRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(httpsRequestFactory);
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8));
        List list = Lists.newArrayList();
        restTemplate.setInterceptors(list);
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError (ClientHttpResponse response) {
                return false;
            }

            @Override
            public void handleError (ClientHttpResponse response) {
            }
        });
        return restTemplate;
    }

    @Bean (name = "httpsRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory () throws Exception {
        CloseableHttpClient httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory httpsFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpsFactory.setReadTimeout(120000);
        httpsFactory.setConnectTimeout(60000);
        return httpsFactory;
    }

}

