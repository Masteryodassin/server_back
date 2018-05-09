package com.tp.server_back.services;

import com.tp.server_back.entities.Data;
import com.tp.server_back.repository.DataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService extends AbstractService<Data, DataRepository>{

    public List<Data> getDatasByLabelandServerId(long serverId, long labelId, String timeStart, String timeEnd){
        return  repository.getDatasByLabelandServerId(serverId, labelId, timeStart, timeEnd);

    }
}
