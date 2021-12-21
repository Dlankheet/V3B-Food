package com.hu.bep3.vkb5.customer.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hu.bep3.vkb5.customer.infrastructure.driven.messaging.RabbitMqEventPublisher;
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

	// Exchange names
	@Value("${messaging.exchange.food}")
	private String foodExchangeName;

	// Queue names
	@Value("${messaging.queue.customers}")
	private String customersQueueName;
	@Value("${messaging.queue.orders}")
	private String ordersQueueName;
	@Value("${messaging.queue.reviews}")
	private String reviewsQueueName;

	// Routing key names
	@Value("${messaging.routing-key.customers}")
	private String customersRoutingKey;
	@Value("${messaging.routing-key.orders}")
	private String ordersRoutingKey;
	@Value("${messaging.routing-key.reviews}")
	private String reviewsRoutingKey;

	// Exchanges
	@Bean
	public TopicExchange foodExchange() {
		return new TopicExchange(foodExchangeName);
	}

	// Queue's
	@Bean
	public Queue customersQueue() {
		return QueueBuilder.durable(customersQueueName).build();
	}
	@Bean
	public Queue ordersQueue() {
		return QueueBuilder.durable(ordersQueueName).build();
	}
	@Bean
	public Queue reviewsQueue() {
		return QueueBuilder.durable(reviewsQueueName).build();
	}

	// Bindings
	@Bean
	public Binding customerBinding() {
		return BindingBuilder
				.bind(customersQueue())
				.to(foodExchange())
				.with(customersRoutingKey);
	}
	@Bean
	public Binding orderBinding() {
		return BindingBuilder
				.bind(ordersQueue())
				.to(foodExchange())
				.with(ordersRoutingKey);
	}
	@Bean
	public Binding reviewBinding() {
		return BindingBuilder
				.bind(reviewsQueue())
				.to(foodExchange())
				.with(reviewsRoutingKey);
	}

	// General RabbitMQ config
	@Bean
	public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
		return new RabbitMqEventPublisher(template, foodExchangeName);
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
