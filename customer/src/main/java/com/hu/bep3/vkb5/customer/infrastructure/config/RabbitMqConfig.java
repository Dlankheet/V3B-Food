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

	// Routing key names
	@Value("${messaging.routing-key.customers}")
	private String customersRoutingKey;

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

	// Bindings
	@Bean
	public Binding candidatesKeywordsBinding() {
		return BindingBuilder
				.bind(customersQueue())
				.to(foodExchange())
				.with(customersRoutingKey);
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
