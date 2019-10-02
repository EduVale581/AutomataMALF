/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class Transicion {
    private Estado inicio;
    private Estado fin;
    private char caracter;

    public Transicion(Estado inicio, Estado fin, char caracter) {
        this.inicio = inicio;
        this.fin = fin;
        this.caracter = caracter;
    }

    public Estado getInicio() {
        return inicio;
    }

    public void setInicio(Estado inicio) {
        this.inicio = inicio;
    }

    public Estado getFin() {
        return fin;
    }

    public void setFin(Estado fin) {
        this.fin = fin;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
    
    @Override
    public String toString(){
        return "("+inicio + ", " + caracter + ", " + fin + ")"; 
        
    }
    
    
    
}
