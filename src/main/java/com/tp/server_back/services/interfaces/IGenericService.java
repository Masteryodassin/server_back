package com.tp.server_back.services.interfaces;


import java.io.IOException;
import java.util.List;


/**²
 * Created by nico on 23/05/17.
 */
public interface IGenericService<T>{

    void save(T entity) ;
    void save(List<T> item);
    void delete(T entity);
    void delete(Long id) ;
    List <T> findAll() ;
    T findOne(Long ID) ;
}
