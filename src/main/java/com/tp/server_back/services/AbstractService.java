package com.tp.server_back.services;

import com.tp.server_back.services.interfaces.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


import java.io.IOException;
import java.util.List;


public abstract class AbstractService<T, D extends CrudRepository<T, Long>> implements IGenericService<T> {

    protected AbstractService(){}

    @Autowired
    protected D repository;

    @Override
    public void save(T item) {
        this.repository.save(item);
    }

    @Override
    public void save(List<T> item) {
        this.repository.save(item);
    }

    @Override
    public void delete(T item)  {
        this.repository.delete(item);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }

    @Override
    public List<T> findAll()  {
        return (List<T>) this.repository.findAll();
    }

    @Override
    public T findOne(Long ID) {
        return repository.findOne(ID);
    }
}
