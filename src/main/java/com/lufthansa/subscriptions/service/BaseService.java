package com.lufthansa.subscriptions.service;

import com.lufthansa.subscriptions.exception.NotFoundException;
import com.lufthansa.subscriptions.repository.BaseRepository;

import java.util.List;

public abstract class BaseService<T> {

    protected BaseRepository<T> entityRepository;

    public BaseService(BaseRepository<T> entityRepository) {
        this.entityRepository = entityRepository;
    }

    public T save(T entity) {
        return entityRepository.save(entity);
    }

    public T findById(Long id, String entity) {
        return entityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("%s with ID: %d was not found!", entity, id)));
    }

    public void delete(T entity) {
        entityRepository.delete(entity);
    }

    public List<T> findAll() {
        return entityRepository.findAll();
    }

    public void saveAll(List<T> entityList) {
        entityRepository.saveAll(entityList);
    }

    public void deleteAll(List<T> entityList) {
        entityRepository.deleteAll(entityList);
    }
}
