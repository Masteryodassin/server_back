package com.tp.server_back.services.utils;

import com.tp.server_back.entities.Data;
import com.tp.server_back.entities.Label;
import com.tp.server_back.entities.Server;
import com.tp.server_back.services.DataService;
import com.tp.server_back.services.LabelService;
import com.tp.server_back.services.ServerService;
import com.tp.server_back.services.utils.storage.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileParsingService {

    @Autowired
    private ServerService serverService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private DataService dataService;

    @Autowired
    FileSystemStorageService fileSystemStorageService;git

    private BufferedReader br;
    private String[] fieldNames;


    public FileParsingService(ServerService serverService, LabelService labelService,
                              DataService dataService, FileSystemStorageService fileSystemStorageService) throws IOException {

        this.serverService = serverService;
        this.labelService = labelService;
        this.dataService = dataService;
        this.serverService = serverService;
        this.fileSystemStorageService = fileSystemStorageService;

    }

    public void loadAll(){
        List<String> paths = fileSystemStorageService
                .loadAll()
                .map(path ->
                        path.getFileName().toString())
                .collect(Collectors.toList());

        try {
            for (String path:paths
                 ) {
                uploadFile(fileSystemStorageService.getPath() + "/" + path);
            }

        }catch(ArrayIndexOutOfBoundsException ae){
            ae.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }


    public void uploadFile(String filePath){
        File file = new File(filePath);
        List<String> lines = readFile(file);

        Server server = new Server();
        server.setName(file.getName());
        this.serverService.save(server);
        List<Label> labels = new ArrayList<>();
        String[] tabLines = lines.get(0).split(";");

        int indexTime = 1;

        for (int i = 2;i<tabLines.length;i++){
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

}