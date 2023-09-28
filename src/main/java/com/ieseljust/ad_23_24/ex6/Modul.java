/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.ad_23_24.ex6;

import java.io.Serializable;

/**
 *
 * @author joange
 * @author Samuel
 *
 */
public class Modul implements Serializable {

    private static final long serialVersionUID = 1L;

    String nom, professor;
    int hores;
    double nota;

    public Modul(String nom, int hores, double nota, String professor) {
        this.nom = nom;
        this.hores = hores;
        this.nota = nota;
        this.professor = professor;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setHores(int hores) {
        this.hores = hores;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
    
    

    public String getModul() {
        return this.nom;
    }

    public String getProfessor() {
        return professor;
    }

    public int getHores() {
        return this.hores;
    }

    public double getNota() {
        return this.nota;
    }

    @Override
    public String toString() {
        return "Modul{" + "nom=" + nom + ", hores=" + hores + ", nota=" + nota + '}';
    }

}
