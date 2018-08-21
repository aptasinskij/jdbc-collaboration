package edu.aptasinskij.jdbc.day2.dao;


/**
 * General interface for all DAO implementations
 * @param <T> - Type of entity
 * @param <E> - Type of identifier
 * */
public interface CrudDao<T, E> {

    void create(T entity);

    T select(E id);

    void update(T entity);

    void delete(T entity);

}
