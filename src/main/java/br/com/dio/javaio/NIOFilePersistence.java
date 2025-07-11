package br.com.dio.javaio;

import java.io.*;
import java.nio.ByteBuffer;

public class NIOFilePersistence implements FilePersistence{
    private final String currentDir = System.getProperty("user.dir");

    private final String storeDir = "/managedFiles/NIO/";

    private final String fileName;

    public NIOFilePersistence(String fileName) throws IOException {
        this.fileName = fileName;
        var file = new File(currentDir + storeDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();

    }

    @Override
    public String write(final String data) {
       try(var file = new RandomAccessFile(new File(currentDir + storeDir + fileName), "rw");

               ){
           file.seek(file.length());
           file.writeBytes(data);
           file.writeBytes(System.lineSeparator());
       } catch (IOException ex ) {
           ex.printStackTrace();
       }
        return data ;
    }

    @Override
    public boolean remove(String sentence) {
        return false;
    }

    @Override
    public String replace(final String oldContent, final  String newContent) {
        return "";
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        try(var file = new RandomAccessFile(new File(currentDir + storeDir + fileName),"r");
            var channel = file.getChannel();
        ){

            var buffer = ByteBuffer.allocate(256);
            var byteReader = channel.read(buffer);
            while (byteReader != -1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    content.append((char) buffer.get());
                }
                buffer.clear();
                byteReader = channel.read(buffer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        return "";
    }
    private void clearFile(){
        try(OutputStream outputStream = new FileOutputStream(currentDir + storeDir + fileName)) {
//         System.out.printf("Inicializando recursos (%s) \n " , currentDir + storeDir + fileName);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
