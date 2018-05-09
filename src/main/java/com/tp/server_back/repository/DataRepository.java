package com.tp.server_back.repository;

import com.tp.server_back.entities.Data;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends CrudRepository<Data, Long> {

        @Modifying
        @Query("SELECT da FROM Data AS da, Label AS la, Server AS se WHERE (se.id) = (:serverId) " +
                "AND (la.id) = (:labelId) AND (da.time) >= (:timeStart) AND (da.time) <= (:timeEnd)")
        List<Data> getDatasByLabelandServerId(@Param("serverId")long serverId,
                                        @Param("labelId")long labelId,
                                        @Param("timeStart") String timeStart,
                                        @Param("timeEnd") String timeEnd);
}
