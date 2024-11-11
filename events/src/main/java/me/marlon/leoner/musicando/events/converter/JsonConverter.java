package me.marlon.leoner.musicando.events.converter;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonConverter {

    private final Gson gson;

    public String serialize(Object object) {
        return gson.toJson(object);
    }

    public <T> T deserialize(Object object, Class<T> type) {
        if (object instanceof String value) return gson.fromJson(value, type);
        return gson.fromJson(gson.toJson(object), type);
    }
}
