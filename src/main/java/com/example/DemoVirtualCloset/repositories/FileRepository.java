package com.example.DemoVirtualCloset.repositories;

public interface FileRepository<ID, T> {

    T save(T entity);

    T findById(ID id);
}