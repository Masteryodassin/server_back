package com.tp.server_back.repository;

import com.tp.server_back.entities.Data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {

        @Query("SELECT Data FROM Data RIGHT JOIN Server as s LEFT JOIN Label as l WHERE s.id =  AND la.id = d.label_id")
        public List<Data> getDatasByLabelandServerId(@Param("serverId")long serverId, @Param("labelId")long labelId);
}
