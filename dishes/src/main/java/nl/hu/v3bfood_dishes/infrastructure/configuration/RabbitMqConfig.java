package nl.hu.v3bfood_dishes.infrastructure.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.v3bfood_dishes.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/*
    Values are configured in application.properties

    Docs: https://docs.spring.io/spring-amqp/docs/current/reference/html/#reference
    Concepts: https://www.rabbitmq.com/tutorials/amqp-concepts.html
 */

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.jobboard}")
    private String jobBoardExchangeName;

    @Value("${messaging.queue.candidate-keywords}")
    private String candidateKeywordsQueueName;
    @Value("${messaging.queue.job-keywords}")
    private String jobKeywordsQueueName;
    @Value("${messaging.queue.all-keywords}")
    private String allKeywordsQueueName;

    @Value("${messaging.routing-key.candidate-keywords}")
    private String candidatesKeywordsRoutingKey;
    @Value("${messaging.routing-key.job-keywords}")
    private String jobsKeywordsRoutingKey;
    @Value("${messaging.routing-key.all-keywords}")
    private String keywordsRoutingKey;

    @Bean
    public TopicExchange jobBoardExchange() {
        return new TopicExchange(jobBoardExchangeName);
    }

    @Bean
    public Queue candidatesQueue() {
        return QueueBuilder.durable(candidateKeywordsQueueName).build();
    }

    @Bean
    public Binding candidatesKeywordsBinding() {
        return BindingBuilder
                .bind(candidatesQueue())
                .to(jobBoardExchange())
                .with(candidatesKeywordsRoutingKey);
    }

    @Bean
    public Queue jobsQueue() {
        // Creates a new queue in RabbitMQ
        return QueueBuilder.durable(jobKeywordsQueueName).build();
    }

    @Bean
    public Binding jobsKeywordsBinding() {
        return BindingBuilder
                .bind(jobsQueue())
                .to(jobBoardExchange())
                .with(jobsKeywordsRoutingKey);
    }

    @Bean
    public Queue keywordsQueue() {
        // Creates a new queue in RabbitMQ
        return QueueBuilder.durable(allKeywordsQueueName).build();
    }

    @Bean
    public Binding keywordsBinding() {
        return BindingBuilder
                .bind(keywordsQueue())
                .to(jobBoardExchange())
                .with(keywordsRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, jobBoardExchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}
