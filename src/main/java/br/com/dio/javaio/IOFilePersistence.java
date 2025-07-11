package br.com.dio.javaio;

import java.io.*;


public class IOFilePersistence extends FilePersistence {

    public IOFilePersistence(final String fileName) throws IOException {
        super(fileName , "/managedFiles/IO/");
        var file = new File(currentDir + storeDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();


    }

    @Override
    public String write(final String data) {
        try (
                var fileWrite = new FileWriter(currentDir + storeDir + fileName, true);
                var bufferedWrite = new BufferedWriter(fileWrite);
                var printWriter = new PrintWriter(bufferedWrite)
        ){
            printWriter.println(data);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data ;
    }


    @Override
    public String findAll() {
        var content = new  StringBuilder();
        try (var reader = new BufferedReader(new FileReader(currentDir + storeDir + fileName))){

            String line;
            do {
                line = reader.readLine();
                if ((line!= null )) content.append(line)
                        .append(System.lineSeparator());

            }while (line != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(final String sentence) {
        var found ="";
        try (var reader = new BufferedReader(new FileReader(currentDir + storeDir + fileName))){

            String line = reader.readLine();
            while (line != null){
                if ((line.contains(sentence))){
                    found = line;
                    break;
                }
                line = reader.readLine();
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return found;
    }


}
