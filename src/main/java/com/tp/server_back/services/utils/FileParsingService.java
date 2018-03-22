package com.tp.server_back.services.utils;


import com.tp.server_back.entities.Colonne;
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
        colonnes = this.createColonnes(serverMaps);

        this.createServer(file.getName(),serverMaps.get(0),colonnes);
        serverService.save(this.server);
    }


    private BufferedReader br;
    private Colonne colonne;
    private List<Server> servers = new ArrayList<>();
    private List <Colonne> colonnes;
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
        colonnes = this.createColonnes(serverMaps);

        FileParsingService.getInstance().createServer(file.getName(),serverMaps.get(0),colonnes);
        serverService.save(this.server);


    }*/

    /**
     * create server from parsed map adding its colonnes as a list
     * @param fileName
     * @param map
     * @param colonneList
     */
    private void createServer(String fileName, Map<String, String> map,List<Colonne> colonneList){

        server = new Server();

        server.setName(fileName);

        String value = map.get("memory_used");

        server.setColonnes(colonneList);
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

        Map<String, String[]> fields = new HashMap<>();

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
     * Use the list of datas for populating the list colonnes
     * @param parsedDatas
     */
    public List<Colonne> createColonnes (List<Map<String,String>> parsedDatas){


        List<Colonne> colonnes = new ArrayList<>();
        Map<String,Method> setters = this.getSetters(Colonne.class);
        Map map;
        String setterName;
        Method setter;


        for (int i = 0; i < parsedDatas.size(); i++){

            map = parsedDatas.get(i);
            colonne = new Colonne();

            for(int j = 0 ; j < fieldNames.length; j++) {

                setterName =  fieldNames[j];
                setter = setters.get(setterName);

                if(setter != null) {

                    Class<?>[] classes = setter.getParameterTypes();

                    try {
                         if (Double.class.isAssignableFrom(classes[0])) {

                             if (map.get(setterName).equals("")) {
                                 setter.invoke(colonne, Double.valueOf(0));
                             } else {
                                 setter.invoke(colonne, Double.valueOf((String) map.get(fieldNames[j])));
                            }
                        } else {
                             if (map.get(setterName).equals("")){
                                 setter.invoke(colonne, Float.valueOf(0));
                             }else {
                                 setter.invoke(colonne, Float.valueOf((String) map.get(fieldNames[j])));
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

            colonnes.add(colonne);
        }

        return colonnes;

        // set time values

        /*colonne.setTimestamp(Timestamp.valueOf(map.get(fieldNames[0])));
        colonne.setHumantime(Date.valueOf(map.get(fieldNames[0])));

        // set Traffic values

        colonne.setTraffic_in(Double.valueOf(map.get(fieldNames[0])));
        colonne.setTraffic_out(Double.valueOf(map.get(fieldNames[0])));

        //set Memory and security error values

        colonne.setMemory_used(Double.valueOf(map.get(fieldNames[0])));
        colonne.setSecurity_error(Float.valueOf(map.get(fieldNames[0])));

        // set Cpu usage values

        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));
        colonne.setCpu0(Float.valueOf(map.get(fieldNames[0])));

        // set Disk Usage

        colonne.setDisk_used(Double.valueOf(map.get(fieldNames[0])));*/


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
