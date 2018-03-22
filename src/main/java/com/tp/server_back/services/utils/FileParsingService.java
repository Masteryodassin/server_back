package com.tp.server_back.services.utils;


import com.tp.server_back.entities.Server;
import com.tp.server_back.entities.ServerInfo;
import com.tp.server_back.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
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
        serverInfos = this.createServerInfos(serverMaps);

        this.createServer(file.getName(),serverMaps.get(0),serverInfos);
        serverService.save(this.server);
    }


    private BufferedReader br;
    private ServerInfo serverInfo;
    private List<Server> servers = new ArrayList<>();
    private List <ServerInfo> serverInfos;
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
        serverInfos = this.createServerInfos(serverMaps);

        FileParsingService.getInstance().createServer(file.getName(),serverMaps.get(0),serverInfos);
        serverService.save(this.server);


    }*/

    /**
     * create server from parsed map adding its serverInfos as a list
     * @param fileName
     * @param map
     * @param serverInfoList
     */
    private void createServer(String fileName, Map<String, String> map,List<ServerInfo> serverInfoList){

        server = new Server();

        server.setName(fileName);

        String value = map.get("memory_used");


        if (map.get("memory_used").isEmpty()){
            server.setMemory_size( Double.valueOf(0));
        }else{
            server.setMemory_size( Double.valueOf(map.get("memory_used")));
        }

        if (map.get("disk_used").isEmpty()){
            server.setMemory_size( Double.valueOf(0));
        }else{
            server.setMemory_size( Double.valueOf(map.get("disk_used")));
        }

        server.setServerInfos(serverInfoList);
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
     * parse each lines of the list into a map of fields as Key and corresponding data as value
     * @param list
     */
    public List<Map<String,String>> parseLines (List<String> list){

        List<Map<String, String>> parsedDatas = new ArrayList<>();

        Map<String, String> fields = new HashMap<>();

        String fieldName = list.get(0);
        fieldNames = fieldName.split(",");

        try {

            for (int i = 1; i < list.size(); i++) {

                String data = list.get(i);
                String[] dataNames = data.split(",");

                for (int j = 0; j < fieldNames.length; j++) {
                    if (dataNames[j].equals(null)){
                        fields.put(fieldNames[j], null);
                    }else {
                        fields.put(fieldNames[j], dataNames[j]);
                    }
                }

                parsedDatas.add(fields);

            }
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }


        return parsedDatas;
    }


    /**
     * Use the list of datas for populating the list serverInfos
     * @param parsedDatas
     */
    public List<ServerInfo> createServerInfos (List<Map<String,String>> parsedDatas){


        List<ServerInfo> serverInfos = new ArrayList<>();
        Map<String,Method> setters = this.getSetters(ServerInfo.class);
        Map map;
        String setterName;
        Method setter;


        for (int i = 0; i < parsedDatas.size(); i++){

            map = parsedDatas.get(i);
            serverInfo = new ServerInfo();

            for(int j = 0 ; j < fieldNames.length; j++) {

                setterName =  fieldNames[j];
                setter = setters.get(setterName);

                if(setter != null) {

                    Class<?>[] classes = setter.getParameterTypes();

                    try {
                         if (Double.class.isAssignableFrom(classes[0])) {

                             if (map.get(setterName).equals("")) {
                                 setter.invoke(serverInfo, Double.valueOf(0));
                             } else {
                                 setter.invoke(serverInfo, Double.valueOf((String) map.get(fieldNames[j])));
                            }
                        } else {
                             if (map.get(setterName).equals("")){
                                 setter.invoke(serverInfo, Float.valueOf(0));
                             }else {
                                 setter.invoke(serverInfo, Float.valueOf((String) map.get(fieldNames[j])));
                             }
                        }

                    } catch (IllegalAccessException iae) {
                        iae.printStackTrace();
                    } catch (InvocationTargetException ite) {
                        ite.printStackTrace();
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }
                }
            }

            serverInfos.add(serverInfo);
        }

        return serverInfos;

        // set time values

        /*serverInfo.setTimestamp(Timestamp.valueOf(map.get(fieldNames[0])));
        serverInfo.setHumantime(Date.valueOf(map.get(fieldNames[0])));

        // set Traffic values

        serverInfo.setTraffic_in(Double.valueOf(map.get(fieldNames[0])));
        serverInfo.setTraffic_out(Double.valueOf(map.get(fieldNames[0])));

        //set Memory and security error values

        serverInfo.setMemory_used(Double.valueOf(map.get(fieldNames[0])));
        serverInfo.setSecurity_error(Float.valueOf(map.get(fieldNames[0])));

        // set Cpu usage values

        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        serverInfo.setCpu0(Float.valueOf(map.get(fieldNames[0])));

        // set Disk Usage

        serverInfo.setDisk_used(Double.valueOf(map.get(fieldNames[0])));*/


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
