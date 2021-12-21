package nl.vkb.review.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.vkb.review.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${messaging.exchange.food}")
	private String reviewExchangeName;
	@Value("${messaging.queue.review}")
	private String reviewQueueName;

	@Value("${messaging.routing-key.review-update}")
	private String reviewUpdateRoutingKey;

	@Bean
	public TopicExchange reviewExchange() {
		return new TopicExchange(reviewExchangeName);
	}

	@Bean
	public Queue reviewsQueue() {
		return QueueBuilder.durable(reviewQueueName).build();
	}

	@Bean
	public Binding reviewBinding() {
		return BindingBuilder
				.bind(reviewsQueue())
				.to(reviewExchange())
				.with(reviewUpdateRoutingKey);
	}

	@Bean
	public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
		return new RabbitMqEventPublisher(template, reviewExchangeName);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory());

		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder
				.createXmlMapper(false)
				.build();

		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

		converter.setAlwaysConvertToInferredType(true);

		return converter;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory(host, port);
	}
}
