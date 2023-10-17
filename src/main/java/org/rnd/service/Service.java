package org.rnd.service;

import java.util.List;

import org.rnd.model.Model;
import org.rnd.model.SearchResult;
import org.rnd.repository.Repository;
import org.rnd.service.common.BaseService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Service extends BaseService {

    @Inject
    Repository repository;

    /**
     * 
     * @param id
     * @return
     */
    public Model get(Long id) {
        return repository.findById(id);
    }

    /**
     * 
     * @param entity
     */
    public void detachEntity(Object entity) {
        super.detachEntity(entity);
    }

    public List<SearchResult> search(Double longitude, Double latitude) {
        return repository.search(longitude, latitude);
    }
}
