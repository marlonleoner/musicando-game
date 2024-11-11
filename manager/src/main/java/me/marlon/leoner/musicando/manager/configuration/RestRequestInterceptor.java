package me.marlon.leoner.musicando.manager.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RestRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        log.info("=============== Request ===============");
        log.info("URI    : {}", request.getURI());
        log.info("Method : {}", request.getMethod());
        log.info("Headers: {}", request.getHeaders());
        log.info("Body   : {}", new String(body, StandardCharsets.UTF_8));
        log.info("=======================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));

        log.info("=============== Response ===============");
        log.info("Status : {} {}", response.getStatusText(), response.getStatusCode());
        log.info("Headers: {}", response.getHeaders());
//        log.info("Body   : {}", body);
        log.info("=======================================");

    }
}
