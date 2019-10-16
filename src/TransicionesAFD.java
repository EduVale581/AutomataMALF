
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
public class TransicionesAFD {
    int id;
    String estados;
    ArrayList<Integer> alfabeto = new ArrayList<>();

    public TransicionesAFD(int id, String estados, ArrayList<Integer> alfabeto) {
        this.id = id;
        this.estados = estados;
        this.alfabeto = alfabeto;
    }

    public TransicionesAFD() {
    }

    public String getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }

    public ArrayList<Integer> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<Integer> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
}
