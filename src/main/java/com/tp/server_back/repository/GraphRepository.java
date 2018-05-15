package com.tp.server_back.repository;

import com.tp.server_back.entities.Graph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ybunetel on 15/05/2018.
 */
@Repository
public interface GraphRepository extends CrudRepository<Graph, Long> {
}
