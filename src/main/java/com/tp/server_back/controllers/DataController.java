package com.tp.server_back.controllers;

import com.tp.server_back.entities.Data;
import com.tp.server_back.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @PostMapping(value="/data")
    public List<Data> displayData(@RequestBody RequestData requestData) {

        long serverId = requestData.getServerId();
        long labelId = requestData.getLabelId();
        String timeStart = requestData.getTimeStart();
        String timeEnd =requestData.getTimeEnd();

        return dataService.getDatasByLabelandServerId(serverId, labelId, timeStart, timeEnd);

    }

    /**
     * Inner class specifying the requestBody for querying datas regarding one server and label type
     * in between two timestamps
     */
    private static class RequestData {
        long serverId;
        long labelId;
        String timeStart;
        String timeEnd;

        public RequestData(){}


        public long getServerId() {
            return serverId;
        }

        public void setServerId(long serverId) {
            this.serverId = serverId;
        }

        public long getLabelId() {
            return labelId;
        }

        public void setLabelId(long labelId) {
            this.labelId = labelId;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }
    }

}
