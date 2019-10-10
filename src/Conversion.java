
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
        
        nuevoEstadoInicio = new Estado(0);

        
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
    public AutomataNoDeterminista convertirCaracter(char valor){
        AutomataNoDeterminista nuevo = new AutomataNoDeterminista();
        
        Estado nuevoEstadoInicio;
        nuevoEstadoInicio = new Estado(0);
      //  a.addEstados(nuevoEstadoInicio);
        nuevo.addEstados(nuevoEstadoInicio);
        nuevo.setInicio(nuevoEstadoInicio);
        
        Estado nuevoEstadoFin = new Estado(1);
        nuevo.addEstados(nuevoEstadoFin);
        nuevoEstadoFin.setVerificacion(true);
        nuevo.setTermino(nuevoEstadoFin);
       // a.addEstados(nuevoEstadoFin);
        
        Transicion nueva = new Transicion(nuevoEstadoInicio, nuevoEstadoFin, valor);
       // a.addTanciciones(nueva);
        nuevo.addTanciciones(nueva);
        nuevo.actualizarEstadoFinales();
        nuevo.actualizarEstados();
        return nuevo;
    }
    /**
     * Concatena dos automatas uniendo el final del primero con el primero del ultimo
     * @param automataFinal
     * @param aux
     * @return 
     */
    public AutomataNoDeterminista concatenar(AutomataNoDeterminista automataFinal, AutomataNoDeterminista aux){
        aux.getEstados().get(0).setIdentificador(automataFinal.getEstados().size());
        aux.actualizarIdentificador();
        automataFinal.getTermino().setVerificacion(false);
        Transicion nueva = new Transicion(automataFinal.getTermino(), aux.getInicio(), '_');
        for (int i = 0; i < aux.getEstados().size(); i++) {
            automataFinal.addEstados(aux.getEstados().get(i));
        }
        for (int i = 0; i < aux.getTanciciones().size(); i++) {
            automataFinal.addTanciciones(aux.getTanciciones().get(i));
        }
        automataFinal.addTanciciones(nueva);
        automataFinal.actualizarEstadoFinales();
        automataFinal.actualizarEstados();
        
        return automataFinal;

    }
    
    public AutomataNoDeterminista disyuncion(AutomataNoDeterminista automataFinal, AutomataNoDeterminista aux){
        automataFinal.getTermino().setVerificacion(false);
        aux.getTermino().setVerificacion(false);
        
        automataFinal.actualizar();
        Estado nuevoInicio = new Estado(0);
        automataFinal.getEstados().add(0, nuevoInicio);
        
        aux.getEstados().get(0).setIdentificador(automataFinal.getEstados().size());
        aux.actualizarIdentificador();
        
        for (int i = 0; i < aux.getEstados().size(); i++) {
            automataFinal.addEstados(aux.getEstados().get(i));
        }
        for (int i = 0; i < aux.getTanciciones().size(); i++) {
            automataFinal.addTanciciones(aux.getTanciciones().get(i));
        }
        
        Estado nuevoFinal = new Estado(automataFinal.getEstados().size());
        nuevoFinal.setVerificacion(true);
        automataFinal.addEstados(nuevoFinal);
        
        Transicion inicial1= new Transicion(nuevoInicio, automataFinal.getInicio(), '_');
        Transicion inicial2 = new Transicion(nuevoInicio, aux.getInicio(), '_'); 
        Transicion final1 = new Transicion(automataFinal.getTermino(),nuevoFinal,'_');
        Transicion final2 = new Transicion(aux.getTermino(),nuevoFinal,'_');
        
        automataFinal.addTanciciones(inicial1);
        automataFinal.addTanciciones(inicial2);
        automataFinal.addTanciciones(final1);
        automataFinal.addTanciciones(final2);
        
        
        automataFinal.actualizarEstadoFinales();
        automataFinal.actualizarEstados();
        
        return automataFinal;
    }
    
    public AutomataNoDeterminista clausura(AutomataNoDeterminista automataFinal){
        automataFinal.getTermino().setVerificacion(false);
        Estado inicioViejo = automataFinal.getInicio();
        Estado finalViejo = automataFinal.getTermino();
                
        automataFinal.actualizar();
        Estado nuevoInicio = new Estado(0);
        automataFinal.getEstados().add(0, nuevoInicio);
        
        Estado nuevoFinal = new Estado(automataFinal.getEstados().size());
        nuevoFinal.setVerificacion(true);
        automataFinal.addEstados(nuevoFinal);
        
        Transicion inicial = new Transicion(nuevoInicio, inicioViejo, '_');
        Transicion vuelta = new Transicion(finalViejo,inicioViejo,'_');
        Transicion termino = new Transicion(finalViejo,nuevoFinal,'_');
        Transicion termino2 = new Transicion(nuevoInicio,nuevoFinal,'_');
        
        automataFinal.addTanciciones(inicial);
        automataFinal.addTanciciones(termino2);
        automataFinal.addTanciciones(vuelta);
        automataFinal.addTanciciones(termino);
        
        automataFinal.actualizarEstadoFinales();
        automataFinal.actualizarEstados();
        
        return automataFinal;

    }
    
    public void convertirAFNDaAFD(AutomataNoDeterminista afnd){
        
        Estado estadoInicial = afnd.getInicio();
        Estado estadoFinal = afnd.getTermino();
        for (int i = 0; i < estadoFinal.getIdentificador(); i++) {
            Estado aux = buscarEstado(i, afnd);
            System.out.println(i);
            epsilon(aux, afnd);
            
        }
        /*ArrayList<Integer> estados = new ArrayList<>();
        ArrayList<Integer> estados2 = new ArrayList<>();
        estados.add(estadoInicial.getIdentificador());
        for (int i = 0; i < afnd.getTanciciones().size(); i++) {
            if (afnd.getTanciciones().get(i).getInicio().getIdentificador() == estadoInicial.getIdentificador() && afnd.getTanciciones().get(i).getCaracter()=='_') {
                estados.add(afnd.getTanciciones().get(i).getFin().getIdentificador());
                
            }
        }
        
        for (int i = 0; i < estados.size(); i++) {
            System.out.print(estados.get(i));
            System.out.print(" ");
        }
        System.out.println();
        estados2.add(estadoInicial.getIdentificador());
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < afnd.getTanciciones().size(); j++) {
                if (afnd.getTanciciones().get(j).getInicio().getIdentificador() == estados.get(i) && afnd.getTanciciones().get(j).getCaracter()=='_') {
                    estados2.add(afnd.getTanciciones().get(j).getFin().getIdentificador());
                }
            }
        }
        
        for (int i = 0; i < estados2.size(); i++) {
            System.out.print(estados2.get(i));
            System.out.print(" ");
        }
        System.out.println();*/
        
        
    }
    
    private void epsilon(Estado nuevo, AutomataNoDeterminista afnd){
        ArrayList<Integer> estados = new ArrayList<>();
        ArrayList<Integer> estados2 = new ArrayList<>();
        estados.add(nuevo.getIdentificador());
        for (int i = 0; i < afnd.getTanciciones().size(); i++) {
            if (afnd.getTanciciones().get(i).getInicio().getIdentificador() == nuevo.getIdentificador() && afnd.getTanciciones().get(i).getCaracter()=='_') {
                estados.add(afnd.getTanciciones().get(i).getFin().getIdentificador());
                
            }
        }
        
        /*for (int i = 0; i < estados.size(); i++) {
            System.out.print(estados.get(i));
            System.out.print(" ");
        }
        System.out.println();*/
        estados2.add(nuevo.getIdentificador());
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < afnd.getTanciciones().size(); j++) {
                if (afnd.getTanciciones().get(j).getInicio().getIdentificador() == estados.get(i) && afnd.getTanciciones().get(j).getCaracter()=='_') {
                    estados2.add(afnd.getTanciciones().get(j).getFin().getIdentificador());
                }
            }
        }
        
        for (int i = 0; i < estados2.size(); i++) {
            System.out.print(estados2.get(i));
            System.out.print(" ");
        }
        System.out.println();
        
        //return estados2;
        
    }
    
    private Estado buscarEstado (int i, AutomataNoDeterminista afnd){
        for (int j = 0; j < afnd.getTanciciones().size(); j++) {
            if (i==afnd.getTanciciones().get(j).getInicio().getIdentificador()) {
                return afnd.getTanciciones().get(j).getInicio();
            }
        }
        return null;
    }

    
}
