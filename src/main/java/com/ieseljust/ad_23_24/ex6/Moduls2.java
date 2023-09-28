/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.ex6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author joange
 * @author samuel
 */
public class Moduls2 {

    // Arrays with source data
    String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimèdia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
    String[] professorat = {"Samuel Mascarell", "Jose Alfredo", "Ferran", "Jose Alfredo", "Alicia", "Rafa"};
    int[] hores = {6, 3, 6, 5, 5, 3};
    double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

    public void writeObject(String nom) throws IOException {

        //destination file
        ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(nom));

        Modul m; // Single object

        // loop along the arrays
        for (int i = 0; i < this.moduls.length; i++) {
            m = new Modul(this.moduls[i], this.hores[i], this.notes[i], this.professorat[i]);
            f.writeObject(m);
        }

        // close the file
        f.close();

    }

    public void readObject(String nom) throws IOException, ClassNotFoundException {

        // input file
        ObjectInputStream f = new ObjectInputStream(new FileInputStream(nom));

        Modul m;
        // we don't know how many objects exists in the file.
        try {
            while (true) { // forever

                m = (Modul) f.readObject();

                // show the module
                System.out.println("Modul: " + m.getModul());
                System.out.println("Hores: " + m.getHores());
                System.out.println("Nota: " + m.getNota());
                System.out.println("Professor: " + m.getProfessor());
                System.out.println();

            }
        } catch (EOFException ex) {
            f.close();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // test the args
        if (args.length != 2) {
            System.out.println("Wrong number of arguments.\n\nSintax: \n\t java Moduls2 [ read | write ] file.obj");
            System.exit(0);
        }

        Moduls2 moduls = new Moduls2();
        System.out.println(args[0] + args[1]);
        // depending the args
        if (args[0].equals("read")) {
            moduls.readObject(args[1]);
        } else if (args[0].equals("write")) {
            moduls.writeObject(args[1]);
        } else {
            System.out.println("I dont undestand what to do... " + args[0] + "\n");
        }

    }

}
