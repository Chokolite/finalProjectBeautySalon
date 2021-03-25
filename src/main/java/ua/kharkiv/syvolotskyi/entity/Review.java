package ua.kharkiv.syvolotskyi.entity;

public class Review extends Entity {
    private String review;
    private Long rating;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
