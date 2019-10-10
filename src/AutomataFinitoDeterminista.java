
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class AutomataFinitoDeterminista {
    private ArrayList<Estado> estados = new ArrayList<>();
    private ArrayList<String> alfabeto  = new ArrayList<>();
    private ArrayList<Transicion> tanciciones= new ArrayList<>();
    private ArrayList<Estado> estadosIniciales = new ArrayList<>();
    private ArrayList<Estado> estadosFinales = new ArrayList<>();
    //private ArrayList<Estado> estadosAceptacion = new ArrayList<>();
    AutomataNoDeterminista afnd;

    public AutomataFinitoDeterminista(AutomataNoDeterminista afnd) {
        this.afnd = afnd;
    }
    
    
    
    
    public void addEstados(Estado nuevo){
        estados.add(nuevo);
    }
    
    public void addAlfabeto(String nuevo){
        alfabeto.add(nuevo);
    }
    
    public void addTransiciones (Transicion nuevo){
        tanciciones.add(nuevo);
    }
    
    public void addEstadosIniciales (Estado nuevo){
        estadosIniciales.add(nuevo);
    }
    
    public void addEstadosFinales (Estado nuevo){
        estadosFinales.add(nuevo);
    }
    
    
    
}
