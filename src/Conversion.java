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
    
    public AutomataNoDeterminista conversionConcatenacion (char valor, AutomataNoDeterminista a){
        Estado nuevoEstadoInicio;
        if (a.getEstados()==null) {
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
        //afnd.mostrarAFND();
        System.out.println(a.getEstados().size());
        return a;
    }
    
    public AutomataNoDeterminista conversionConcatenacion2 (AutomataNoDeterminista a, AutomataNoDeterminista b, AutomataNoDeterminista c){
        Estado nuevo = null;
        System.out.println(a.getEstados().size());
        //System.out.println(b.getEstados().size());

        Transicion nueva = new Transicion(nuevo, b.getInicio(), '_');
        c.addTanciciones(nueva);
        c.mostrarAFND();
        return c;
    }
    

    
    
}
