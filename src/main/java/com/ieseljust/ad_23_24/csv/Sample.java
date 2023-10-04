/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joange
 * @author samuel
 */
public class Sample {

    private void showCSV(String filename, boolean isHeader, String separator) throws IOException {

        // Read one file by one Line
        List<String> records = Files.readAllLines(Paths.get(filename));

        //print headers, only if exists
        if (isHeader) {
            String line = records.get(0);
            records.remove(0);
            String[] headers = line.split(separator);
            int i = 0;
            for (String header : headers) {
                System.out.printf("%15s", header.toUpperCase());
                i += 15;
            }
            System.out.println("");
            imprimirCaracterRepe("-", i + headers.length + 1);

        }

        // print values
        for (String record : records) {
            String[] values = record.split(separator);
            for (String value : values) {
                System.out.printf("%20s", value);
            }
            System.out.println("");
        }
    }

    private void imprimirCaracterRepe(String car, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(car);
        }
        System.out.println();

    }

    private void showCSVCols(String filename, boolean isHeader, String separator) throws IOException {

        // Read one file by one Line
        List<String> records = Files.readAllLines(Paths.get(filename));

        int[] colsSize = calculateMaxColSize(records, separator);

        //print headers, only if exists
        if (isHeader) {
            String line = records.get(0);
            records.remove(0);
            String[] headers = line.split(separator);
            int tam = 0;
            System.out.print("|");
            for (int i = 0; i < headers.length; i++) {
                System.out.printf("%" + colsSize[i] + "s|", headers[i]);
                tam += colsSize[i];
            }
            tam += headers.length + 1;
            System.out.println("");
            imprimirCaracterRepe("-", tam);
        }

        // print values
        for (String record : records) {
            String[] values = record.split(separator);
            System.out.print("|");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%" + colsSize[i] + "s|", values[i]);
            }
            System.out.println("");
        }
    }

    private int[] calculateMaxColSize(List<String> lines, String separator) {
        // array containing the maximun sizes
        int[] maxColSizes = new int[lines.get(0).split(separator).length];

        for (String line : lines) {
            String elements[] = line.split(separator);
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].length() > maxColSizes[i]) {
                    maxColSizes[i] = elements[i].length();
                }
            }
        }

        return maxColSizes;
    }

    private void addLineToCSV(String filename) throws IOException {
//        List<String[]> dataLines = new ArrayList<>();
//        List<String> datalines = Files.readAllLines(Paths.get(filename));
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy-hhmmss").format(Calendar.getInstance().getTime());
//        datalines.add("Samuel " + timeStamp);
        FileWriter output = new FileWriter(filename, true);
        output.write("Samuel " + timeStamp + "\n");
        output.close();
//        dataLines.add(new String[]{"John Doe",",","AAA",",","Starting Pitcher",",", "70",",","178",",","28.9"});
//        for(String line : dataLines.get(0)) {
//            String[] values = line.split(",");  
//            for(String valor : values) {
//                System.out.println(valor);
//            }
//        }

//        dataLines.add(new String[]{"Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\""});
    }

    public static void main(String[] args) throws IOException {

        String filename = "mlb_players.csv";

        Sample sam = new Sample();
        sam.addLineToCSV("mlb_players.csv");

        sam.showCSV(filename, true, ",");
//        sam.showCSVCols(filename, true, ",");
        
    }
}
