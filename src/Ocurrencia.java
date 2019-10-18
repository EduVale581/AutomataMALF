
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class Ocurrencia {
    
    private String cadena;
    private AutomataFinitoDeterminista afd;
    private ArrayList<String> alfabeto;

    public Ocurrencia(String cadena, AutomataFinitoDeterminista afd, ArrayList<String> alfabeto) {
        this.cadena = cadena;
        this.afd = afd;
        this.alfabeto = alfabeto;
    }
    
    
    public String leer(int linea,String estadoActual){
        int contadorAuxiliar = 1;
        char cadenaActual = ' ';
        System.out.print("Linea"+linea+": ");
        for (int i = 0; i < cadena.length(); i++) {
            cadenaActual = cadena.charAt(i);
            if (!alfabeto.contains(Character.toString(cadenaActual))) {
                System.out.println("Hay una letra no perteneciente al alfabeto definido");
                return "";
            }
            for (int j = 0; j < afd.tranciciones.size(); j++) {   
                if (estadoActual == afd.tranciciones.get(j).inicial) {
                    if (cadenaActual == afd.tranciciones.get(j).letra) {
                        estadoActual = afd.tranciciones.get(j).finall; 
                        if (afd.verificarfinal(estadoActual)) {
                            System.out.print(" "+contadorAuxiliar);

                        }
                        break;
                    }
                }
            }
            contadorAuxiliar++;
        }
        System.out.println("");
        return estadoActual;
    }
}
