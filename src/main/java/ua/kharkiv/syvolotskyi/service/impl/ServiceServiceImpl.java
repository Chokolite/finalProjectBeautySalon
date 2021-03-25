package ua.kharkiv.syvolotskyi.service.impl;

import ua.kharkiv.syvolotskyi.entity.Service;
import ua.kharkiv.syvolotskyi.repository.ServiceRepository;
import ua.kharkiv.syvolotskyi.service.ServiceService;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    private TransactionManager transactionManager;
    private ServiceRepository serviceRepository;

    public ServiceServiceImpl(TransactionManager transactionManager, ServiceRepository serviceRepository) {
        this.transactionManager = transactionManager;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service getById(Long id) {
        return transactionManager.execute(c -> serviceRepository.getById(c, id));
    }

    @Override
    public List<Service> getAll() {
        return transactionManager.execute(c -> serviceRepository.getAll(c));
    }

    @Override
    public Long save(Service service) {
        return transactionManager.execute(c -> serviceRepository.save(c, service));
    }

    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            serviceRepository.delete(c, id);
            return null;
        });
    }
}
