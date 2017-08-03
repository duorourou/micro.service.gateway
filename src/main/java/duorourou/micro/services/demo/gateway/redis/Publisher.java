package duorourou.micro.services.demo.gateway.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void publish(String cacheName, String cacheKey) {
        LOGGER.info("published a msg : cache Name -> {} , key -> {}", cacheName, cacheKey);
        stringRedisTemplate.convertAndSend("cache-updating", cacheName + "," + cacheKey);
    }
}
