package nl.vkb.review;

import nl.vkb.review.Service.ReviewQueryService;
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
	private final ReviewQueryService service;

	public QueueReceiver(ReviewRepository repository, ReviewQueryService service) {
		this.repository = repository;
		this.service = service;
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@RabbitListener(queuesToDeclare = @Queue("review.submit"))
	public void reviewListener(ReviewDTO reviewDTO) {
		Review review = service.getReview(reviewDTO.id);
		repository.save(review);
	}
}
