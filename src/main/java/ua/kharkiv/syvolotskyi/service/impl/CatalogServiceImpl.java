package ua.kharkiv.syvolotskyi.service.impl;

import ua.kharkiv.syvolotskyi.entity.Catalog;
import ua.kharkiv.syvolotskyi.repository.CatalogRepository;
import ua.kharkiv.syvolotskyi.service.CatalogService;
import ua.kharkiv.syvolotskyi.transaction.TransactionManager;

import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    private TransactionManager transactionManager;
    private CatalogRepository catalogRepository;

    public CatalogServiceImpl(TransactionManager transactionManager, CatalogRepository catalogRepository) {
        this.transactionManager = transactionManager;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> getAll(String name, String type, String orderBy, int offset, int size) {
        return transactionManager.execute(c -> catalogRepository.getAll(c, name, type, orderBy, offset, size));
    }

    @Override
    public Catalog getById(Long id) {
        return transactionManager.execute(c -> catalogRepository.getById(c, id));
    }

    @Override
    public Long save(Catalog catalog) {
        return transactionManager.execute(c -> catalogRepository.save(c, catalog));
    }

    @Override
    public void delete(Long id) {
        transactionManager.execute(c -> {
            catalogRepository.delete(c, id);
            return null;
        });
    }
}
