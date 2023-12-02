package com.example.DemoVirtualCloset.repositories;


import java.util.Optional;
public interface FileRepository<ID, T> {

    T save(T entity);
    Optional<T> findById(ID id);

    void deleteById(ID id);

}