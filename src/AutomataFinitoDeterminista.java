
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
    ArrayList<String> estados = new ArrayList<>();
    ArrayList<String> alfabeto  = new ArrayList<>();
    ArrayList<TransicionAFD> tranciciones= new ArrayList<>();
    String estadoInicial;
    ArrayList<String> estadoAceptacion= new ArrayList<>();
    
    private Estado estadoFinal;
    private Estado estadoInicio;

    public AutomataFinitoDeterminista() {
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }
    
    public void addEstados(String nuevo){
        estados.add(nuevo);
    }
    
    public void addAlfabeto(String nuevo){
        alfabeto.add(nuevo);
    }
    
    public void addTransiciones (TransicionAFD nuevo){
        tranciciones.add(nuevo);
    }
    
    public void addEstadosAceptacion (String nuevo){
        estadoAceptacion.add(nuevo);
    }
    public void mostrar(){
        System.out.println("AFD:");
        System.out.print("K = { ");
        for (int i = 0; i < this.estados.size(); i++) {
            System.out.print(this.estados.get(i) + " ");
        }
        System.out.println("}");
        System.out.print("Sigma = { ");
        for (int i = 0; i < this.alfabeto.size(); i++) {
            System.out.print(this.alfabeto.get(i) + " ");
        }
        System.out.println("}");
        System.out.println("delta:");
        for (int i = 0; i < this.tranciciones.size(); i++) {
            System.out.println("(" + this.tranciciones.get(i).getInicial() + ", " + this.tranciciones.get(i).getLetra() + ", " + this.tranciciones.get(i).getFinall() + ")");
            
        }
        System.out.println("s = " + this.getEstadoInicial());
        System.out.print("F = { ");
        for (int i = 0; i < this.estadoAceptacion.size(); i++) {
            System.out.print(this.estadoAceptacion.get(i) + " ");
        }
        System.out.println("}");
    }
    
    
    public boolean verificarfinal(String estado){
        for (int i = 0; i < estadoAceptacion.size(); i++) {
            if (estado == null ? estadoAceptacion.get(i) == null : estado.equals(estadoAceptacion.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    
}
