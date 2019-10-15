
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
public class EstadoAFD {
    String valor;
    ArrayList<Integer> estados;
    char letra;

    public EstadoAFD(String valor, ArrayList<Integer> estados) {
        this.valor = valor;
        this.estados = estados;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }
    
    

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ArrayList<Integer> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Integer> estados) {
        this.estados = estados;
    }
    
    public void mostrar(){
        System.out.print(this.valor + " ");
        for (int i = 0; i < estados.size(); i++) {
            System.out.print(estados.get(i) + " ");
        }
        System.out.print(this.letra + " ");
        System.out.println();
    }
}
