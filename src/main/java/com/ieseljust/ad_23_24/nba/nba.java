/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.nba;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author samuel
 */
public class nba {

    public static void main(String[] args) throws IOException {

        String line;
        System.out.println("NBA Players Dictionary");
        System.out.println("----------------------------");

        BufferedReader fin;
        fin = new BufferedReader(new FileReader(args[0]));
        DataOutputStream fout = new DataOutputStream(new FileOutputStream(args[1]));

        int lineNumber = 0;

        while ( (line = fin.readLine()) != null) {
            // Read the line
            
            String[] parts = line.split("\t");

            if (lineNumber == 0) {
                for (String part : parts) {
                    fout.writeUTF(part);
                }
            }
            else {
                fout.writeUTF(parts[0]);
                fout.writeUTF(parts[1]);
                fout.writeUTF(parts[2]);
                fout.writeUTF(parts[3]);
                fout.writeUTF(parts[4]);
                fout.writeInt(Integer.parseInt(parts[5]));
                fout.writeInt(Integer.parseInt(parts[6]));
                fout.writeUTF("\n");
            }
            lineNumber++;
        }

        fin.close();
        fout.close();
    }
}
