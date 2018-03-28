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
        @Query("SELECT Data FROM Data as da, Label as la, Server as se WHERE (se.id) = (:serverId) AND (la.id)= (:labelId)")
        List<Data> laMerdeVoilaCestTout(@Param("serverId")long serverId, @Param("labelId")long labelId);
}
