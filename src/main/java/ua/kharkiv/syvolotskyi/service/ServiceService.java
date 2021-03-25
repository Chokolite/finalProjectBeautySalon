package ua.kharkiv.syvolotskyi.service;

import ua.kharkiv.syvolotskyi.entity.Service;

import java.util.List;

public interface ServiceService {
    Service getById(Long id);

    List<Service> getAll();

    Long save(Service service);

    void delete(Long id);
}
