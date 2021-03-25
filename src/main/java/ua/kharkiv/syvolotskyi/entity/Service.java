package ua.kharkiv.syvolotskyi.entity;

public class Service extends Entity{
    private String serviceDuration;
    private Long servicePrice;

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public Long getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Long servicePrice) {
        this.servicePrice = servicePrice;
    }
}
