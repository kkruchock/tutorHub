package ru.itis.tutorhub.repositories.review;

import ru.itis.tutorhub.models.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository {

    Review save(Review review);

    List<Review> findByTutorId(UUID tutorId);

    Optional<Review> findByApplicationId(UUID applicationId);

    void updateTutorRating(UUID tutorId);
}