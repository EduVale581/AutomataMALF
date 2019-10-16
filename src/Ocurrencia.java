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
        System.out.print("Linea"+linea+": ");
        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < afd.tranciciones.size(); j++) {   
                if (estadoActual == afd.tranciciones.get(j).inicial) {
                    if (cadena.charAt(i) == afd.tranciciones.get(j).letra) {
                        if (afd.verificarfinal(estadoActual)) {
                            System.out.print(" "+contadorAuxiliar);
                        }
                        estadoActual = afd.tranciciones.get(j).finall;
                        
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
