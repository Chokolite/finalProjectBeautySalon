package ua.kharkiv.syvolotskyi.service;

import ua.kharkiv.syvolotskyi.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();

    Long save(Review review);

}
