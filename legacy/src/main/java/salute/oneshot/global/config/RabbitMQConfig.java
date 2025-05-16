package salute.oneshot.global.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${RABBITMQ_QUEUE_EVENT}")
    private String queueName;

    @Value("${RABBITMQ_EXCHANGE_EVENT}")
    private String exchangeName;

    @Value("${RABBITMQ_ROUTING_KEY_EVENT}")
    private String routingKey;

    @Bean
    Queue eventQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange eventExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue eventQueue, DirectExchange eventExchange) {
        return BindingBuilder.bind(eventQueue)
                .to(eventExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        // 메시지 전송 후 확인을 위한 설정 (선택적)
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("메시지 전송 실패: " + (cause != null ? cause : "알 수 없는 오류"));
            }
        });
        return rabbitTemplate;
    }
}
