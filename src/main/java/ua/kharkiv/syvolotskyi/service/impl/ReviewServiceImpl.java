package ua.kharkiv.syvolotskyi.service.impl;

import ua.kharkiv.syvolotskyi.entity.Review;
import ua.kharkiv.syvolotskyi.repository.ReviewRepository;
import ua.kharkiv.syvolotskyi.service.ReviewService;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    TransactionManager transactionManager;
    ReviewRepository reviewRepository;

    public ReviewServiceImpl(TransactionManager transactionManager, ReviewRepository reviewRepository) {
        this.transactionManager = transactionManager;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAll() {
        return transactionManager.execute(c-> reviewRepository.getAll(c));
    }

    @Override
    public Long save(Review review) {
        return transactionManager.execute(c-> reviewRepository.save(c, review));
    }
}
