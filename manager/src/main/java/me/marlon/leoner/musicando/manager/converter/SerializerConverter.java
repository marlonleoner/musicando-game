package me.marlon.leoner.musicando.manager.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SerializerConverter {

    private final ObjectMapper mapper;

    public String serialize(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public <T> T deserialize(String message, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(message, type);
    }
}
