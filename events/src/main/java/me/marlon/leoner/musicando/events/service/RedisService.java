package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

//    private final RedisTemplate<String, Object> redisTemplate;
//
//    private final JsonConverter converter;
//
//    public void set(String key, Object object) {
//        redisTemplate.opsForValue().set(key, object, 60, TimeUnit.MINUTES);
//    }
//
//    public <T> T get(String key, Class<T> type) {
//        Object object = redisTemplate.opsForValue().get(key);
//        return converter.deserialize(object, type);
//    }
//
//    public <T> List<T> getAll(String key, Class<T> type) {
//        Set<String> keys = redisTemplate.keys(key);
//        if (Objects.isNull(keys) || keys.isEmpty()) return Collections.emptyList();
//
//        log.info("========= keys {}", keys);
//
//        return Stream.of(keys).map(k -> {
//            String kstr = k.iterator().next();
//            if (Objects.isNull(kstr)) return null;
//            Object object = redisTemplate.opsForValue().get(kstr);
//            return converter.deserialize(object, type);
//        }).filter(Objects::nonNull).toList();
//    }
//
//    public void remove(String key) {
//        redisTemplate.delete(key);
//    }
//
//    public void setItemInList(String key, Object object) {
//        redisTemplate.opsForList().rightPush(key, object);
//        redisTemplate.expire(key, 60, TimeUnit.MINUTES);
//    }
//
//    public void setInList(String key, List<?> object) {
//        object.forEach(obj -> setItemInList(key, obj));
//    }
//
//    public <T> T getItemInList(String key, Integer index, Class<T> type) {
//        Long size = redisTemplate.opsForList().size(key);
//        if (Objects.isNull(size) || size == 0) return null;
//
//        return converter.deserialize(redisTemplate.opsForList().index(key, index), type);
//    }
//
//    public void removeList(String key) {
//        redisTemplate.opsForList().trim(key, 0, size(key));
//    }
//
//    public Integer size(String key) {
//        Long size = redisTemplate.opsForList().size(key);
//        return Objects.isNull(size) ? 0 : Math.toIntExact(size);
//    }
//
//    public void setItemInHash(String key, String hashKey, Object object) {
//        redisTemplate.opsForHash().put(key, hashKey, object);
//    }
//
//    public <T> T getItemInHash(String key, String hashKey, Class<T> type) {
//        Object object = redisTemplate.opsForHash().get(key, hashKey);
//        return converter.deserialize(object, type);
//    }
//
//    public <T> List<T> getItemsInHash(String key, Class<T> type) {
//        Set<Object> keys = redisTemplate.opsForHash().keys(key);
//        if (keys.isEmpty()) return Collections.emptyList();
//
//        return keys.stream()
//                .map(hashKey -> getItemInHash(key, String.valueOf(hashKey), type))
//                .toList();
//    }
//
//    public void removeItemInHash(String key, String hashKey) {
//        redisTemplate.opsForHash().delete(key, hashKey);
//    }
}
