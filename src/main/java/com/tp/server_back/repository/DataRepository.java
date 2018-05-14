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
        @Query("SELECT dat.time, dat.value, dat.label.id FROM Data AS dat, " +
                "Server AS se, Label AS la " +
                "WHERE (se.id) = (la.server.id) AND " +
                "(se.id) = (:serverId) AND " +
                "(la.id) = (dat.label.id) AND " +
                "(la.id) = (:labelId) AND " +
                "(dat.time) >= (:timeStart) AND (dat.time) <= (:timeEnd)")
        List<Data> getDatasByLabelandServerId(@Param("serverId")long serverId,
                                        @Param("labelId")long labelId,
                                        @Param("timeStart") String timeStart,
                                        @Param("timeEnd") String timeEnd);
}
