package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestService {

    private final RestClient restClient;

    public <T> T get(String url, Class<T> type) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(type);
    }

    public <T> T post(String url, Object body, Class<T> type) {
        return restClient.post()
                .uri(url)
                .body(body)
                .retrieve()
                .body(type);
    }

    public <T> List<T> postList(String url, Object body) {
        return restClient.post()
                .uri(url)
                .body(body)
                .retrieve()
                .body(new ParameterizedTypeReference<List<T>>() {});
    }
}
