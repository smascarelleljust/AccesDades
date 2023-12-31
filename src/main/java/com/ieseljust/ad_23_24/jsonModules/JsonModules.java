/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.jsonModules;

import com.ieseljust.ad_23_24.ex6.Modul;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

//import com.google.gson.Gson;
/**
 *
 * @author joange
 */
public class JsonModules {

    ArrayList<Modul> Course;

    private void createCourse() {
        // Aquest mètode inicializa l'objecte "Course" de la classe JSONLib
        // que no és més que un vector de mòduls
        Course = new ArrayList<>();

        // Definim els vectors per inicialitzar dades
        String[] moduls = {"Accés a Dades", "Programació de serveis i processos", "Desenvolupament d'interfícies", "Programació Multimèdia i dispositiud mòbils", "Sistemes de Gestió Empresarial", "Empresa i iniciativa emprenedora"};
        int[] hores = {6, 3, 6, 5, 5, 3};
        double[] notes = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

        // Recorrem els vectors, creant els objectes
        // de tipus Modul i guardant-los en Course
        for (int i = 0; i < moduls.length; i++) {
            Modul m = new Modul(moduls[i], hores[i], notes[i]);
            this.Course.add(m);
        }

    }

    private JSONObject createJSON() {

        // root element "Course"
        JSONObject course = new JSONObject();

        // who is a JSONArray
        JSONArray jsarray = new JSONArray();

        // we populate the array with individual modules
        for (Modul m : this.Course) {
            JSONObject modul_json = m.getModulJSON();
            jsarray.put(modul_json);
        }

        // Create the course element with the array
        course.put("course", jsarray);

        return (course);

    }

    private void writeJSON(String filename, JSONObject jso) {

        try {
            FileWriter file = new FileWriter(filename);
            file.write(jso.toString(4)); // 4 són els espais d'indentació
            file.close();

        } catch (IOException e) {
            System.out.println("Error, no es pot crear el fitxer " + filename);
        }
    }

    private JSONObject readJSON(String filename) {
        try {
            // Amb FileReader llegirem caràcter a 
            // caràcter el fitxer i l'afegim al string myJson
            FileReader file = new FileReader(filename);
            String myJson = "";

            int i;
            while ((i = file.read()) != -1) {
                myJson = myJson + ((char) i);
            }

            //System.out.println(myJson);
            file.close();

            // I fem ús del constructor de JSONObject
            // al que li passem un string amb el JSON:
            return (new JSONObject(myJson));

        } catch (Exception e) {
            System.out.println("Error llegint el fitxer");
            return null;
        }
    }

    private void showJSON(JSONObject json) {

        // amb el mètode getJSONArray obtenim el primer
        // element "course", que era una llista
        JSONArray jsa = json.getJSONArray("curs");

        // I ara recorrem aquesta llista:
        for (int i = 0; i < jsa.length(); i++) {
            // Agafem cada element de l'array amb "get"
            JSONObject modul = (JSONObject) jsa.get(i);
            // Amb el get anterior tindrem objectes JSON 
            // de mòduls, tipus:
            // {"nom": "Modul", "hores": hores, "nota": nota }
            // Als valors d'aquests parells també accedirem amb get:
            System.out.println("Modul: " + modul.get("nom"));
            System.out.println("Hores: " + modul.get("hores"));
            System.out.println("Nota: " + modul.get("nota"));

            /*
        En esta funció escrivim els objectes JSON. Si volguérem
        crear de nou l'estructura d'objectes, crearíem cadascun 
        dels mòduls amb:
            Modul m=new Modul(modul.get("nom"), modul.get("hores"), modul.get("nota"));
             */
        }

    }

//    private void loadWithGSON(String filename) throws IOException{
//
//        String jsonFile=new String(Files.readAllBytes(Paths.get(filename)));
//        
//
//          Gson gson=new Gson();
//          
//          Modul [] elsModuls=gson.fromJson(jsonFile, Modul[].class);
//          for (Modul m : elsModuls) {
//              System.out.println(m);
//        }
//          
//    }
    public static void main(String[] args) throws IOException {

        JsonModules jsonM = new JsonModules();

        jsonM.createCourse();

        JSONObject obj = jsonM.createJSON();

        jsonM.writeJSON("moduls.json", obj);

        JSONObject obj2 = jsonM.readJSON("moduls.json");

        jsonM.showJSON(obj2);

//        System.out.println("------ Gson ------");
//        jsonM.loadWithGSON("moduls_1.json");
    }

}
