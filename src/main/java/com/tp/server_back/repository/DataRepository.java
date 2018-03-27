package com.tp.server_back.repository;

import com.tp.server_back.entities.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {
        List<Data> getDatasByLabelandServerId(long serverId,long labelId);
}
