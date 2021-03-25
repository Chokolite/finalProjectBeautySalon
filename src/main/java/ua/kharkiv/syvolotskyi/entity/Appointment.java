package ua.kharkiv.syvolotskyi.entity;

import java.time.LocalDateTime;

public class Appointment extends Entity {
    private Entity client;
    private Catalog catalog;
    private LocalDateTime localDateTime;
    private Status status;
    private Long reviewId;

    public Appointment() {}

    public Appointment(Long id) { setId(id);}

    public Entity getClient() {
        return client;
    }

    public void setClient(Entity client) {
        this.client = client;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }
}
