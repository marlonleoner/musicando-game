package me.marlon.leoner.musicando.manager.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration {

    private final RestRequestInterceptor interceptor;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new RestRequestInterceptor()));

        return restTemplate;
    }

    @Bean
    public RestClient restClient() {
        return getBaseBuilder()
                .defaultHeader("Origin", "https://music.apple.com")
                .defaultHeader("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IldlYlBsYXlLaWQifQ.eyJpc3MiOiJBTVBXZWJQbGF5IiwiaWF0IjoxNzI5NjM5NjA0LCJleHAiOjE3MzY4OTcyMDQsInJvb3RfaHR0cHNfb3JpZ2luIjpbImFwcGxlLmNvbSJdfQ.xgTYs6rdi-rMI_r5RD_HdIDNnH47wo5YwMo7ffYM3hJPNNQoMCfA_VjvRE0eamxb4LsLCDQC6oVJlPy_M9mc2w")
                .build();
    }

    private RestClient.Builder getBaseBuilder() {
        BufferingClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return RestClient.builder()
                .requestFactory(factory)
                .requestInterceptor(interceptor);
    }
}
