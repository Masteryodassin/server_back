package com.tp.server_back.services.utils;


import com.tp.server_back.entities.Data;
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

    @Autowired
    final ServerService serverService;


    private BufferedReader br;
    private Label label;
    private List<Server> servers = new ArrayList<>();
    private String[] fieldNames;

   /* static FileParsingService instance = new FileParsingService();

    public static FileParsingService getInstance(){
        if (instance == null) return new FileParsingService();
        return instance;
    }*/

    public FileParsingService(ServerService serverService)throws IOException{

        File file = new File("/home/nico/IdeaProject/server_back/resources/test.csv");
        this.serverService = serverService;

        List<Map<String,Map<String,String>>> serverMaps = this.parseColonnes(this.parseFile(file));
        List <Label> labels; labels = this.createColonnes(serverMaps);

        Server server = createServer(file.getName(), labels);
        serverService.save(server);
    }


    /**
     * create server from parsed map adding its labels as a list
     * @param fileName
     * @param labelList
     */
    private Server createServer(String fileName,List<Label> labelList){

        Server server = new Server();
        server.setName(fileName);
        server.setLabels(labelList);
        return server;

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
            if (line != null) {
                parsedFileLines.add(line);
            }
        } while (line != null);

        return parsedFileLines;
    }


    /**
     * parse each lines of the list into a map of fields as Key and corresponding datas as value
     * @param list
     */
    public List<Map<String,Map<String,String>>> parseColonnes (List<String> list){

        List<Map<String, Map<String,String>>> parsedDatas = new ArrayList<>();
        Map<String,String> datas;

        Map<String, Map<String,String>> fields = new HashMap<>();


        String fieldName = list.get(0);
        fieldNames = fieldName.split(",");


        try {
            for (int i = 1; i < list.size(); i++) {

                for (int j = 1 ; j < fieldNames.length; j++) {

                    String[] dataNames = list.get(i).split(",");
                    datas =  new HashMap<>();
                    datas.put(dataNames[0],dataNames[j]);
                    fields.put(fieldNames[j],datas);

                }
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
        List <Data> datas = new ArrayList<>();
        Data data;

        for (Map<String,Map<String,String>> parseData : parsedDatas){


            for (int i = 0; i < fieldNames.length; i++) {


                //time field is implicitly included in the map
                if (!fieldNames[i].equals("time")) {

                label = new Label();
                label.setName(fieldNames[i]);

                    Map<String, String> map = parseData.get(fieldNames[i]);

                    for (Map.Entry<String, String> entry : map.entrySet()
                            ) {

                        data = new Data();
                        data.setTime(entry.getKey());
                        data.setValue(entry.getValue());
                        datas.add(data);

                    }

                    label.setDatas(datas);
                }
                labels.add(label);
            }


        }
        return labels;
    }





    /**
     * get the setters of the class
     * @param targetClass
     * @return map of targetClass setters
     */
  /*  private Map<String, Method> getSetters(Class<?> targetClass) {
        try {
            BeanInfo info = Introspector.getBeanInfo(targetClass);
            return Stream.of(info.getPropertyDescriptors())
                    .filter(Objects::nonNull)
                    .filter(p -> p.getWriteMethod() != null)
                    .collect(Collectors.toMap(PropertyDescriptor::getName, PropertyDescriptor::getWriteMethod));
        } catch (IntrospectionException ex) {
            return Collections.emptyMap();
        }
    }*/

}
