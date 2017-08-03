package duorourou.micro.services.demo.gateway.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqConfig(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String cacheName, String key) {
        rabbitTemplate.convertAndSend(cacheName, key);
    }

}
