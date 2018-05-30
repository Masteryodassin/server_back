package com.tp.server_back.controllers;

import com.tp.server_back.controllers.dtos.DataDto;
import com.tp.server_back.entities.Data;
import com.tp.server_back.services.DataService;
import com.tp.server_back.services.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @Autowired
    LabelService labelService;


    @PostMapping(value="/data")
    public List<DataDto> displayData(@RequestBody RequestData requestData) {

        List<DataDto> dataDtos = new ArrayList<>();
        List<Data> datas;
        DataDto dataDto;
        Map<String, Double> datasMap;
        String name;
        long serverId = requestData.getServerId();
        List<Long> labelIds = requestData.getLabelIds();
        String timeStart = requestData.getTimeStart();
        String timeEnd = requestData.getTimeEnd();

        for (Long id : labelIds
             ) {

            datas = dataService.getDatasByLabelandServerId(serverId, id, timeStart, timeEnd);//.stream().map(Data::new).collect(Collectors.toList());
            name = labelService.findOne(id).getName();
            dataDto = new DataDto(name,id);
            datasMap = new HashMap<>();

            for (int i = 0; i < datas.size(); i++) {

                Data data = datas.get(i);

                if (data.getLabel().getId() == id) {

                    Double value;

                    String key = data.getTime();
                    if (data.getValue().equals("")){
                        value = 0.0;
                    }else {
                        value = Double.parseDouble(data.getValue());
                    }
                    datasMap.put(key, value);
                    dataDto.setDatasMap(datasMap);
                }
            }
            dataDtos.add(dataDto);

        }

        return dataDtos;

    }

    /**
     * Inner class specifying the requestBody for querying datas regarding one server and label type
     * in between two timestamps
     */
    private static class RequestData {
        long serverId;
        List<Long> labelIds;
        String timeStart;
        String timeEnd;

        public RequestData(){}

        public long getServerId() {
            return serverId;
        }

        public void setServerId(long serverId) {
            this.serverId = serverId;
        }

        public List<Long> getLabelIds() {
            return labelIds;
        }

        public void setLabelIds(List<Long> labelIds) {
            this.labelIds = labelIds;
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
