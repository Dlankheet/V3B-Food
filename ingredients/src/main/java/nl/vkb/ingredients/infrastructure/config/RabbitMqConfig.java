package nl.vkb.ingredients.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.vkb.ingredients.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
	private String foodExchangeName;
	@Value("${messaging.queue.stock}")
	private String stockQueueName;

	@Value("${messaging.routing-key.stock-update}")
	private String stockUpdateRoutingKey;

	@Bean
	public TopicExchange foodExchange() {
		return new TopicExchange(foodExchangeName);
	}
	@Bean
	public Queue stockQueue() {
		return QueueBuilder.durable(stockQueueName).build();
	}
	@Bean
	public Binding receptStockBinding() {
		return BindingBuilder
				.bind(stockQueue())
				.to(foodExchange())
				.with(stockUpdateRoutingKey);
	}

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
	public Jackson2ObjectMapperBuilder builder() {
		return new Jackson2ObjectMapperBuilder(); //maybe error?
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
