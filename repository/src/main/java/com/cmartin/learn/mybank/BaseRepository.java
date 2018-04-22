package com.cmartin.learn.mybank;


import com.cmartin.learn.domain.Contract;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by cmartin on 16/07/16.
 */
public interface BaseRepository<T extends Contract, ID extends Serializable> {
    /**
     * @param entity
     * @return
     */
    Optional<T> save(T entity);

    /**
     * @param primaryKey
     * @return
     */
    Optional<T> findOne(ID primaryKey);

    /**
     * @return
     */
    Iterable<T> findAll();

    /**
     * @return
     */
    Long count();

    /**
     * @param entity
     */
    void delete(T entity);

    /**
     * @param primaryKey
     * @return
     */
    Boolean exists(ID primaryKey);
}
