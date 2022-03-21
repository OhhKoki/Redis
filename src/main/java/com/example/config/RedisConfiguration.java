package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 1. 创建对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 2. 设置工厂
        redisTemplate.setConnectionFactory(factory);
        // 3. 创建序列化工具
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 4. key 和 hashKey 采用 String 序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());    // StringRedisSerializer.UTF_8
        redisTemplate.setHashKeySerializer(RedisSerializer.string());    // // StringRedisSerializer.UTF_8
        // 5. value 和 hashValue 采用 JSON 序列化
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        // 6. 返回对象
        return redisTemplate;
    }

}
