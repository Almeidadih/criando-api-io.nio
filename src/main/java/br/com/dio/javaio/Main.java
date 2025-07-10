package br.com.dio.javaio;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
         FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println("===============");
        System.out.println(persistence.write("Diego; diego@Diego.com ;04/07/1999 ;"));
        System.out.println("================");
        System.out.println(persistence.write("Elly; elly@elly.com ;26/12/1990 ;"));
        System.out.println("=================");
        System.out.println(persistence.write("Sandro ; sandro@sandro.com ;04/01/1996 ;"));
        System.out.println("==================");
        System.out.println(persistence.write("Yago; yago@yago.com ; 25/12/2010 ;"));
        System.out.println("==================");
        System.out.println(persistence.findAll());
        System.out.println("===================");
        System.out.println(persistence.remove("/01/1996"));
        System.out.println("====================");
        System.out.println(persistence.remove("/12/2010"));
        System.out.println("====================");
        System.out.println(persistence.findBy("Diego;"));
        System.out.println("==================");
        System.out.println(persistence.findBy("; elly@"));
        System.out.println("==================");
        System.out.println(persistence.findBy("96;"));
        System.out.println("===================");
        System.out.println(persistence.findBy("10"));
        System.out.println("====================");
        System.out.println(persistence.replace(".com;01/1996/" , "Carlos; carlos@carlos.com; 22/03/1991"));
        System.out.println("====================");
        System.out.println(persistence.findAll());

    }
}