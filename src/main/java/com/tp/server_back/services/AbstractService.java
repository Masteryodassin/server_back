package com.tp.server_back.services;

import com.tp.server_back.entities.Server;
import com.tp.server_back.services.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T, D extends CrudRepository<T, Long>> implements IGenericService<T> {

    @Autowired
    protected D repository;

    @Override
    public void save(T item) throws IOException {
        this.repository.save(item);
    }

    @Override
    public void delete(T item) throws IOException {
        this.repository.delete(item);
    }

    @Override
    public void delete(Long id) throws IOException {
        this.repository.delete(id);
    }

    @Override
    public List<T> findAll() throws IOException {
        return (List<T>) this.repository.findAll();
    }

    @Override
    public T findOne(Long ID) throws IOException {
        return repository.findOne(ID);
    }
}
