package org.springframework.samples.reviews.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.samples.reviews.review.Review;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setCompanyId(review.getCompanyId());
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);

    }
}
