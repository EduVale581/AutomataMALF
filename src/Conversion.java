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
    AutomataNoDeterminista afnd = new AutomataNoDeterminista();

    public Conversion() {
    }
    
    public void conversionConcatenacion (char valor){
        Estado nuevoEstadoInicio = new Estado(0);
        Estado nuevoEstadoFin = new Estado(1);
        Transicion nueva = new Transicion(nuevoEstadoInicio, nuevoEstadoFin, valor);
        afnd.setTanciciones(nueva);
        afnd.mostrarAFND();
    }
    
    public void conversionConcatenacion2 (AutomataNoDeterminista a, AutomataNoDeterminista b){
        //Estado nuevoEstadoInicioA = a.
        //Estado nuevoEstadoFinA = new Estado(1);
        Estado nuevoEstadoInicioB = new Estado(0);
        Estado nuevoEstadoFinB = new Estado(1);
        //Transicion nueva = new Transicion(nuevoEstadoInicio, nuevoEstadoFin, valor);
        //afnd.setTanciciones(nueva);
        afnd.mostrarAFND();
    }
    
    
}
