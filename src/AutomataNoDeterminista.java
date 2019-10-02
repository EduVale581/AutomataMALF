
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
public class AutomataNoDeterminista {
    private Estado inicio;
    private ArrayList<Estado> estadosAceptacion;
    private ArrayList<Estado> estados;
    private ArrayList<String> alfabeto;
    private ArrayList<Transicion> tanciciones= new ArrayList<>();

    public AutomataNoDeterminista(Estado inicio, ArrayList<Estado> estadosAceptacion, ArrayList<Estado> estados, ArrayList<String> alfabeto) {
        this.inicio = inicio;
        this.estadosAceptacion = estadosAceptacion;
        this.estados = estados;
        this.alfabeto = alfabeto;
    }

    public AutomataNoDeterminista() {
    }

    public Estado getInicio() {
        return inicio;
    }

    public void setInicio(Estado inicio) {
        this.inicio = inicio;
    }

    public ArrayList<Estado> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(ArrayList<Estado> estadosAceptacion) {
        this.estadosAceptacion = estadosAceptacion;
    }

    public ArrayList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public ArrayList<Transicion> getTanciciones() {
        return tanciciones;
    }

    public void setTanciciones(Transicion tanciciones) {
        this.tanciciones.add(tanciciones);
    }
    
    
    
    public void mostrarAFND(){
        System.out.println("AFND");
        System.out.println("K = ");
        for (int i = 0; i < estados.size(); i++) {
            System.out.print(estados.get(i).getIdentificador());
            
        }
        System.out.println();
        
    }
    
}