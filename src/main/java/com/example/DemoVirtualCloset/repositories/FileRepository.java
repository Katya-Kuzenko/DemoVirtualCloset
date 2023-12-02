package com.example.DemoVirtualCloset.repositories;

import java.io.IOException;
import java.util.Optional;
public interface FileRepository<ID, T> {

    T save(T entity) throws IOException;
    Optional<T> findById(ID id);

    void deleteById(ID id);

    void deleteAll();
}