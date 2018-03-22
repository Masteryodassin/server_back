package com.tp.server_back.services.utils;


import com.tp.server_back.entities.Label;
import com.tp.server_back.entities.Server;
import com.tp.server_back.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileParsingService <T> {

    final ServerService serverService;

   /* static FileParsingService instance = new FileParsingService();

    public static FileParsingService getInstance(){
        if (instance == null) return new FileParsingService();
        return instance;
    }*/

    @Autowired
    public FileParsingService(ServerService serverService)throws IOException{

        File file = new File("/home/nico/IdeaProject/server_back/resources/test.csv");
        this.serverService = serverService;

        serverMaps = this.parseLines(this.parseFile(file));
        labels = this.createColonnes(serverMaps);

        this.createServer(file.getName(),serverMaps.get(0), labels);
        serverService.save(this.server);
    }


    private BufferedReader br;
    private Label label;
    private List<Server> servers = new ArrayList<>();
    private List <Label> labels;
    private List<Map<String,String>> serverMaps;
    private String[] fieldNames;
    private Server server;


    /**
     * Main function of the singleton
     * @throws IOException
     */
   /* public void start () throws IOException{

        File file = new File("/home/nico/IdeaProject/server_back/resources/test.csv");

        serverMaps = this.parseLines(this.parseFile(file));
        labels = this.createColonnes(serverMaps);

        FileParsingService.getInstance().createServer(file.getName(),serverMaps.get(0),labels);
        serverService.save(this.server);


    }*/

    /**
     * create server from parsed map adding its labels as a list
     * @param fileName
     * @param map
     * @param labelList
     */
    private void createServer(String fileName, Map<String, String> map,List<Label> labelList){

        server = new Server();
        server.setName(fileName);
        server.setLabels(labelList);

    }

    /**
     * parse the file into a table of string, each string represent a line of a file
     * @param file
     * @throws IOException
     */
    public List<String> parseFile (File file) throws IOException{

        List<String> parsedFileLines = new ArrayList<>();
        br = new BufferedReader(new FileReader(file));
        String line = null;

        do {
            line = br.readLine();
            parsedFileLines.add(line);
        } while (line != null);

        return parsedFileLines;
    }


    /**
     * parse each lines of the list into a map of fields as Key and corresponding datas as value
     * @param list
     */
    public List<Map<String,Map<String,String>>> parseColonnes (List<String> list){

        List<Map<String, Map<String,String>>> parsedDatas = new ArrayList<>();

        Map<String, Map<String,String>> fields = new HashMap<>();
        Map<String,String> datas =  new HashMap<>();

        String fieldName = list.get(0);
        fieldNames = fieldName.split(",");


        try {

            for (int i = 1; i < fieldNames.length; i++) {

                for (int j = 1 ; j < list.size(); j++) {
                    String[] dataNames = list.get(j).split(",");
                    datas.put(dataNames[0],dataNames[i]);
                }
                fields.put(fieldNames[i],datas);
                parsedDatas.add(fields);
            }

        }catch(NullPointerException npe){
            npe.printStackTrace();
        }

        return parsedDatas;
    }


    /**
     * Use the list of datas for populating the list labels
     * @param parsedDatas
     */
    public List<Label> createColonnes (List<Map<String,Map<String,String>>> parsedDatas){


        List<Label> labels = new ArrayList<>();


            label = new Label();


            labels.add(label);
        }

        return labels;

        // set time values

        /*label.setTimestamp(Timestamp.valueOf(map.get(fieldNames[0])));
        label.setHumantime(Date.valueOf(map.get(fieldNames[0])));

        // set Traffic values

        label.setTraffic_in(Double.valueOf(map.get(fieldNames[0])));
        label.setTraffic_out(Double.valueOf(map.get(fieldNames[0])));

        //set Memory and security error values

        label.setMemory_used(Double.valueOf(map.get(fieldNames[0])));
        label.setSecurity_error(Float.valueOf(map.get(fieldNames[0])));

        // set Cpu usage values

        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        label.setCpu0(Float.valueOf(map.get(fieldNames[0])));

        // set Disk Usage

        label.setDisk_used(Double.valueOf(map.get(fieldNames[0])));*/


    }


    /**
     * get the setters of the class
     * @param targetClass
     * @return map of targetClass setters
     */
    private Map<String, Method> getSetters(Class<?> targetClass) {
        try {
            BeanInfo info = Introspector.getBeanInfo(targetClass);
            return Stream.of(info.getPropertyDescriptors())
                    .filter(Objects::nonNull)
                    .filter(p -> p.getWriteMethod() != null)
                    .collect(Collectors.toMap(PropertyDescriptor::getName, PropertyDescriptor::getWriteMethod));
        } catch (IntrospectionException ex) {
            return Collections.emptyMap();
        }
    }

}
