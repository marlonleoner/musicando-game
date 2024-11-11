package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestService {

    private final RestClient restClient;

    private final JsonConverter converter;

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

    public <T> List<T> postList(String url, Object body, Class<T> type) {
        List<Object> response = restClient.post()
                .uri(url)
                .body(body)
                .retrieve()
                .body(List.class);
        if (CollectionUtils.isEmpty(response)) return Collections.emptyList();

        return response
                .stream()
                .map(obj -> converter.deserialize(obj, type))
                .toList();
    }
}
