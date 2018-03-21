package com.tp.server_back.repository;

import com.tp.server_back.entities.Server;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends CrudRepository <Server, Long>{
}
