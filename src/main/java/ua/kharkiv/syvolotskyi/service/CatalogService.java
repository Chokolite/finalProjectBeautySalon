package ua.kharkiv.syvolotskyi.service;

import ua.kharkiv.syvolotskyi.entity.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> getAll(String name, String type, String orderBy, int offset, int size);

    Catalog getById(Long id);

    Long getCount(String masterName);

    Long save(Catalog catalog);

    void delete(Long id);
}
