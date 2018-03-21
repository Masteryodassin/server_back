package com.tp.server_back.services.interfaces;


import java.io.IOException;
import java.util.List;


/**²
 * Created by nico on 23/05/17.
 */
public interface IGenericService<T>{

    void save(T entity) throws IOException;
    void delete(T entity) throws IOException;
    void delete(Long id) throws IOException;
    List <T> findAll() throws IOException;
    T findOne(Long ID) throws IOException;
}
