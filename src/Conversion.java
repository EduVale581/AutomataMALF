/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class Conversion {

    public Conversion() {
    }
    /**
     * La primera vez que se ejecuta convierte la letra a un automata para 
     * evitar problemas con ID
     * @param valor
     * @param a
     * @return 
     */
    public AutomataNoDeterminista convertirCaracterPrimera (char valor, AutomataNoDeterminista a){
        
        Estado nuevoEstadoInicio;
        if (a.getEstados() == null) {
            nuevoEstadoInicio = new Estado(0);
        }
        else{
            nuevoEstadoInicio = new Estado(a.getEstados().size());
        }
        
        a.addEstados(nuevoEstadoInicio);
        
        Estado nuevoEstadoFin = new Estado(a.getEstados().size());
        a.addEstados(nuevoEstadoFin);
        
        nuevoEstadoFin.setVerificacion(true);
        
        Transicion nueva = new Transicion(nuevoEstadoInicio, nuevoEstadoFin, valor);
        a.addTanciciones(nueva);
        a.actualizarEstadoFinales();
        a.actualizarEstados();
        return a;
        
    }
    /**
     * Se crea un automata auxiliar para luego unirlo al principal
     * @param valor
     * @param a
     * @return 
     */
    public AutomataNoDeterminista convertirCaracter(char valor, AutomataNoDeterminista a){
        AutomataNoDeterminista nuevo = new AutomataNoDeterminista();
        
        Estado nuevoEstadoInicio;
        nuevoEstadoInicio = new Estado(a.getEstados().size());
        a.addEstados(nuevoEstadoInicio);
        nuevo.addEstados(nuevoEstadoInicio);
        nuevo.setInicio(nuevoEstadoInicio);
        
        Estado nuevoEstadoFin = new Estado(a.getEstados().size());
        nuevo.addEstados(nuevoEstadoFin);
        nuevoEstadoFin.setVerificacion(true);
        nuevo.setTermino(nuevoEstadoFin);
        a.addEstados(nuevoEstadoFin);
        
        Transicion nueva = new Transicion(nuevoEstadoInicio, nuevoEstadoFin, valor);
        a.addTanciciones(nueva);
        nuevo.addTanciciones(nueva);
        
        return nuevo;
    }
    /**
     * Concatena dos automatas uniendo el final del primero con el primero del ultimo
     * @param automataFinal
     * @param aux
     * @return 
     */
    public AutomataNoDeterminista concatenar(AutomataNoDeterminista automataFinal, AutomataNoDeterminista aux){
        automataFinal.getTermino().setVerificacion(false);
        Transicion nueva = new Transicion(automataFinal.getTermino(), aux.getInicio(), '_');
        automataFinal.addTanciciones(nueva);
        automataFinal.actualizarEstadoFinales();
        automataFinal.actualizarEstados();
        return automataFinal;

    }
    
    
}
