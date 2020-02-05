package com.wildcodeschool.skillhub.repository;

import java.util.List;

public interface CrudDao<T> {

    T save(T entity);

    T findById(Long userid);

    List<T> findAll(Long filter);

    T update(T entity);

    void deleteById(Long userid);
}