package com.kalanso.event.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service

public abstract class CrudService<T, ID>{

    private final CrudRepository<T, ID> repository;

    public CrudService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }


    public Iterable<T> getAll() {
        return repository.findAll();
    }


    public boolean existsById(ID id) {
        return repository.existsById(id);
    }


    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }


    public T create(T entity) {
        return repository.save(entity);
    }


    /*public T update(ID id, T entity) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Resource not found with id: " + id);
        }
        return repository.save(entity);
    }*/

    public T update(ID id, T entity, String... ignoreProperties) {
        // Vérification si l'entité existe déjà
        T existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));

        // Copie des champs de l'entité existante à l'entité fournie, en ignorant les champs spécifiés
        BeanUtils.copyProperties(entity, existingEntity, ignoreProperties);

        // Sauvegarde de l'entité mise à jour
        return repository.save(existingEntity);
    }


    public void delete(ID id) {
        repository.deleteById(id);
    }
}