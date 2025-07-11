package br.com.dio.javaio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class  FilePersistence {

    protected final String currentDir = System.getProperty("user.dir");

    protected final String storeDir ;

    protected  final String fileName;

    public FilePersistence(final String fileName , final String storeDir) {
        this.storeDir = storeDir;
        this.fileName = fileName;
    }


   public abstract String write(final String data);


    public boolean remove(final String sentence) {
        var contentList = toListString();

        if (contentList.stream().noneMatch(c -> c.contains(sentence))) return  false;

        clearFile();
        contentList.stream()
                .filter(c -> !c.contains(sentence))
                .forEach(this::write);

        return true;
    }


    public String replace(final String oldContent, final String newContent) {
        var contentList = toListString();


        if (contentList.stream().noneMatch(c-> c.contains(oldContent))) return "";

        clearFile();
        contentList.stream()
                .map( c -> c.contains(oldContent) ? newContent : c)
                .forEach(this::write);
        return newContent;
    }

    public abstract String findAll();

    public abstract String findBy(final String sentence);


    protected void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storeDir + fileName)) {
//         System.out.printf("Inicializando recursos (%s) \n " , currentDir + storeDir + fileName);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected List<String> toListString() {
        var content = findAll();
        return new ArrayList<>(Stream.of(content.split(System.lineSeparator())).toList());
    }
}
