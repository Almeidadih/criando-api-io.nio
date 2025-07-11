package br.com.dio.javaio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class NIO2FillePersistence extends   FilePersistence{

//    private final String currentDir = System.getProperty("user.dir");
//    private final String storeDir = "/managedFiles/NIO2/";
//    private final String fileName;

    public NIO2FillePersistence(String fileName) throws IOException {
        super(fileName , "/managedFiles/NIO2/");
        var path = Paths.get(currentDir + storeDir);
        if (!Files.exists(path)){
            Files.createDirectory(path);
        }
            clearFile();
    }

    @Override
    public String write(final String data) {
        var path = Paths.get(currentDir + storeDir + fileName);
        try (var lines = Files.lines(path)){
            Files.write(path , data.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }



    @Override
    public String findAll() {
        var path = Paths.get(currentDir + storeDir + fileName);
        var content = "";
        try (var lines = Files.lines(path)){
            content = lines.collect(Collectors.joining(System.lineSeparator()));
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return content ;
    }

    @Override
    public String findBy(final String sentence) {
        var content = findAll();
        return Stream.of(content.split(System.lineSeparator()))
                .filter(c -> c.contains(sentence))
                .findFirst()
                .orElse("");
    }



}
