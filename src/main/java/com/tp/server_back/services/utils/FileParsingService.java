package com.tp.server_back.services.utils;

import com.tp.server_back.entities.Data;
import com.tp.server_back.entities.Label;
import com.tp.server_back.entities.Server;
import com.tp.server_back.services.DataService;
import com.tp.server_back.services.LabelService;
import com.tp.server_back.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileParsingService {

    @Autowired
    private ServerService serverService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private DataService dataService;

    private BufferedReader br;
    private String[] fieldNames;


    public FileParsingService(ServerService serverService, LabelService labelService, DataService dataService) throws IOException {
        this.serverService = serverService;
        this.labelService = labelService;
        this.dataService = dataService;
        /*File file = new File("/home/nico/IdeaProject/server_back/resources/test.csv");
        this.serverService = serverService;

        List<Map<String, Map<String, String>>> serverMaps = this.parseColonnes(this.parseFile(file));

        List<Label> labels = this.createColonnes(serverMaps);

        Server server = createServer(file.getName(), labels);
        serverService.save(server);*/

        uploadFile("/home/nico/IdeaProject/server_back/resources/esx-alger-01_global.csv");
    }


    public void uploadFile(String filePath){
        File file = new File(filePath);
        List<String> lines = readFile(file);

        Server server = new Server();
        server.setName(file.getName());
        this.serverService.save(server);
        List<Label> labels = new ArrayList<>();
        String[] tabLines = lines.get(0).split(";");

        int indexTime = 0;

        for (int i = 1;i<tabLines.length;i++){
            Label label = new Label();
            label.setName(tabLines[i]);
            label.setServer(server);
            label.setIndexColumn(i);
            labels.add(label);
        }

        labelService.save(labels);

        List<Data> datas = new ArrayList<>();

        for (int i=1;i<lines.size();i++){
            String[] line = lines.get(i).split(";");

            for (Label label : labels){
                Data data = new Data();
                data.setLabel(label);
                data.setTime(line[indexTime]);
                data.setValue(line[label.getIndexColumn()]);
                datas.add(data);
            }
        }
        dataService.save(datas);

    }

    private List<String> readFile(File file){
        try {
            return parseFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * create server from parsed map adding its labels as a list
     *
     * @param fileName
     * @param labelList
     */
    private Server createServer(String fileName, List<Label> labelList) {

        Server server = new Server();
        server.setName(fileName);
        server.setLabels(labelList);
        return server;

    }

    /**
     * parse the file into a table of string, each string represent a line of a file
     *
     * @param file
     * @throws IOException
     */
    public List<String> parseFile(File file) throws IOException {

        List<String> parsedFileLines = new ArrayList<>();
        br = new BufferedReader(new FileReader(file));
        String line = null;

        do {
            line = br.readLine();
            if (line != null) {
                parsedFileLines.add(line);
            }
        } while (line != null);

        return parsedFileLines;
    }


    /**
     * parse each lines of the list into a map of fields as Key and corresponding datas as value
     *
     * @param list
     */
    public List<Map<String, Map<String, String>>> parseColonnes(List<String> list) {

        List<Map<String, Map<String, String>>> parsedDatas = new ArrayList<>();
        Map<String, String> datas;

        Map<String, Map<String, String>> fields = new HashMap<>();


        String fieldName = list.get(0);
        fieldNames = fieldName.split(",");


        try {
            for (int i = 1; i < list.size(); i++) {

                for (int j = 1; j < fieldNames.length; j++) {
                    try {
                        String[] dataNames = list.get(i).split(",");
                        datas = new HashMap<>();
                        datas.put(dataNames[0], dataNames[j]);
                        fields.put(fieldNames[j], datas);
                    } catch (ArrayIndexOutOfBoundsException aie) {
                        aie.printStackTrace();
                        System.out.println(i);
                    }

                }
                parsedDatas.add(fields);
            }


        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        return parsedDatas;
    }


    /**
     * Use the list of datas for populating the list labels
     *
     * @param parsedDatas
     */
    public List<Label> createColonnes(List<Map<String, Map<String, String>>> parsedDatas) {


        List<Map<String, String>> fieldsMaps;
        List<Label> labels = new ArrayList<>();
        List<Data> datas = null;
        Label label = null;
        Data data;
        int logger = 0;


        for (int i = 0; i < fieldNames.length; i++) {

            //time field is implicitly included in the map
            if (!fieldNames[i].equals("time")) {
                label = new Label();
                label.setName(fieldNames[i]);
                fieldsMaps = new ArrayList<>();

                for (Map<String, Map<String, String>> parseData : parsedDatas) {

                    //increase the counter
                    logger++;
                    //log the counter

                    datas = new ArrayList<>();

                    fieldsMaps.add(parseData.get(fieldNames[i]));

                    for (Map<String, String> mapFromList : fieldsMaps
                            ) {

                        if (mapFromList != null) {

                            for (Map.Entry<String, String> entry : mapFromList.entrySet()
                                    ) {
                                data = new Data();
                                data.setTime(entry.getKey());
                                data.setValue(entry.getValue());
                                datas.add(data);

                            }
                        }
                    }
                }
                label.setDatas(datas);
                labels.add(label);
            }
        }
        return labels;
    }

}