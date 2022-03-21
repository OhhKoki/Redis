package com.example;

import com.example.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testString() throws JsonProcessingException {
        // 创建对象
        User user = new User("zhangsan", 18);
        // 手动序列化
        String writeUser = mapper.writeValueAsString(user);
        // 写入
        redisTemplate.opsForValue().set("example:user:101", writeUser);
        // 获取
        String var = redisTemplate.opsForValue().get("example:user:101");
        // 手动反序列化
        User readUser = mapper.readValue(var, User.class);
        // 打印
        System.out.println(readUser);
    }

}
