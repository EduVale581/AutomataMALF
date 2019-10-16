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
    
    public void leer(int linea){
        String estadoActual = afd.getEstadoInicial();
        int contadorAuxiliar = 1;
        char cadenaActual = ' ';
        System.out.print("Linea"+linea+": ");
        for (int i = 0; i < cadena.length(); i++) {
            cadenaActual = cadena.charAt(i);
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
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public AutomataFinitoDeterminista getAfd() {
        return afd;
    }

    public void setAfd(AutomataFinitoDeterminista afd) {
        this.afd = afd;
    }
}
