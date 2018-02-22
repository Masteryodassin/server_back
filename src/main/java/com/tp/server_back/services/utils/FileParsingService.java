package com.tp.server_back.services.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParsingService {

    static FileParsingService instance = new FileParsingService();

    public static FileParsingService getInstance(){
        if (instance == null) return new FileParsingService();
        return instance;
    }

    private FileParsingService(){}

    private File file;
    private String filePath;
    private BufferedReader br;
    private String line;
    private List<String> lines = new ArrayList<>();


    /**
     * parse the file into a table of string, each string represent a line of a file
     * @param file
     * @throws IOException
     */
    public List<String> parseFile (File file ) throws IOException{

        List<String> parsedFileLines = new ArrayList<>();
        br = new BufferedReader(new FileReader(file));

        while (br.readLine() != null){
            line = br.readLine();
            parsedFileLines.add(line);
        }
        return parsedFileLines;
    }





}
