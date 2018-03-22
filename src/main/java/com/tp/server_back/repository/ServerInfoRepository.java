package com.tp.server_back.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerInfoRepository extends CrudRepository<ServerInfo, Long> {
}
