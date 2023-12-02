package com.example.DemoVirtualCloset.repositories;

import java.io.IOException;

public interface FileRepository<ID, T> {

    T save(T entity) throws IOException;
    T findById(ID id);
}