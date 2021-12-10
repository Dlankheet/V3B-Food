package nl.vkb.review;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.domain.Review;
import nl.vkb.review.dto.ReviewDTO;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {
	private final ReviewRepository repository;

	public QueueReceiver(ReviewRepository repository) {
		this.repository = repository;
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queuesToDeclare = @Queue("review.submit"))
	public void reviewListener(ReviewDTO reviewDTO) {
		Review ingredient = repository.findById(reviewDTO.id).orElseThrow(
				() -> new ReviewNotFoundException("Could not be found")
		);
		repository.save(ingredient);
	}
}
