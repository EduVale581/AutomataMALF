
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

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
    
    
    //------------------------------------------------ AFD ------------------------------------------------//
    /**
     * El metodo revisa si los elementos que tiene un arreglo 
     * son iguales.
     * @param uno
     * @param dos
     * @return 
     */
    private boolean igualdad (ArrayList<Integer> uno, ArrayList<Integer> dos){
        Collections.sort(uno);
        Collections.sort(dos);
        return Arrays.deepEquals(uno.toArray(), dos.toArray());
    }
    
    
    /**
     * Este metodo busca un estado en arreglos iguales.
     * @param uno
     * @return 
     */
    private String buscarEstados (ArrayList<Integer> uno){
        for (int i = 0; i < estados2.size(); i++) {
            if (igualdad(estados2.get(i).estados, uno)) {
                return estados2.get(i).getValor();
            }
        }
        return null;
    }
    
    
    /**
     * Este metodo elimina elementos iguales de un arreglo.
     * @param uno
     * @return 
     */
    private ArrayList<Integer> eliminarElementosIguales (ArrayList<Integer> uno){
        Collections.sort(uno);
        Set<Integer> hashSet = new HashSet<>(uno);
        uno.clear();
        uno.addAll(hashSet);
        
        return uno;

    }
    ArrayList<EstadoAFD> estados2 = new ArrayList<>();
    
    /**
     * Este metodo busca lo que puede alcanzar un estado en el automata
     * no determinista con una letra del alfabeto.
     * @param nuevo
     * @param afnd
     * @param c
     * @return 
     */
    private ArrayList<ArrayList<Integer>> buscarAlcanzados(EstadoAFD nuevo, AutomataNoDeterminista afnd, char c){
        ArrayList<ArrayList<Integer>> estadosAlcanzados = new ArrayList<>();
        
        for (int i = 0; i < nuevo.estados.size(); i++) {
            ArrayList<Integer> alcanzado = new ArrayList<>();
            alcanzado.clear();
            for (int j = 0; j < afnd.getTanciciones().size(); j++) {
                if (nuevo.estados.get(i) == afnd.getTanciciones().get(j).getInicio().getIdentificador() && afnd.getTanciciones().get(j).getCaracter()==c) {
                    alcanzado.add(afnd.getTanciciones().get(j).getFin().getIdentificador());
                }
            }
            if (alcanzado.size()>0) {
                estadosAlcanzados.add(alcanzado);
            }
        }
        return estadosAlcanzados;
    }
    
    /**
     * Este metodo pasa la lista de alfabeto en forma de string a una lista de caracteres.
     * @param alfabeto
     * @return 
     */
    private ArrayList<Character> transformarAlfabeto (ArrayList<String> alfabeto){
        ArrayList<Character> alfabe = new ArrayList<>();
        for (int i = 0; i < alfabeto.size(); i++) {
            alfabe.add(alfabeto.get(i).charAt(0));
        }
        return alfabe;
    }
    
    /**
     * Este metodo se encarga de buscar el final del AFD
     * @param uno
     * @param afnd
     * @return 
     */
    private boolean buscarFinales(ArrayList<Integer> uno, AutomataNoDeterminista afnd){
        int finall = afnd.getFinal().getIdentificador();
        for (int i = 0; i < uno.size(); i++) {
            if (finall == uno.get(i)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Este metodo se encarga de buscar estados iguales en el AFND
     * @param uno
     * @return 
     */
    private boolean buscarEstadosIguales (ArrayList<Integer> uno){
        for (int i = 0; i < estados2.size(); i++) {
            if (igualdad(estados2.get(i).estados, uno)) {
                return true;
                
            }
        }
        return false;
    }
    
    /**
     * Este metodo convierte el AFND en una tabla y busca los cercanos.
     * @param nodo
     * @param afnd
     * @param grafo
     * @return 
     */
    private ArrayList<Integer> BFS (int nodo, AutomataNoDeterminista afnd, char [][] grafo){
        ArrayList<Integer> recorridos = new ArrayList<>();
        boolean[] visitados = new boolean [afnd.getEstados().size()+1];
        visitados[nodo] = true;
        ArrayList<Integer> cola = new ArrayList<>();
        recorridos.add(nodo);
        cola.add(nodo);
        
        while (!cola.isEmpty()){
            int j = cola.remove(0);
            for (int i = 0; i < grafo.length; i++) {
                if (grafo[j][i] == '_' && !visitados[i]) {
                    cola.add(i);
                    recorridos.add(i);
                    visitados[i]=true;
                }
                
            }
        }
        return recorridos;
    }
    
    /**
     * Este metodo busca todos los epsilon alcansados por un estado.
     * @param estado
     * @param afnd
     * @return 
     */
    private ArrayList<Integer> clausurasEpsilonDeEstado (int estado, AutomataNoDeterminista afnd){
        int valorMatriz = afnd.getFinal().getIdentificador();
        char [][] posiciones = new char[valorMatriz+1][valorMatriz+1];
        for (int i = 0; i <= valorMatriz; i++) {
            for (int j = 0; j <= valorMatriz; j++) {
                posiciones[i][j] = '0';
            }
        }
        
        for (int i = 0; i < afnd.getTanciciones().size(); i++) {
            posiciones[afnd.getTanciciones().get(i).getInicio().getIdentificador()][afnd.getTanciciones().get(i).getFin().getIdentificador()] = afnd.getTanciciones().get(i).getCaracter();
        }

        ArrayList<Integer> aux = BFS(estado, afnd, posiciones);

        return aux;
    }
    
    /**
     * Es el metodo encargado de convertir el AFND a AFD
     * @param afnd 
     * @return  
     */
    public AutomataFinitoDeterminista convertirAFNDaAFD(AutomataNoDeterminista afnd){
        AutomataFinitoDeterminista nuevoAFD = new AutomataFinitoDeterminista();
        ArrayList<Character> alfabe = transformarAlfabeto(afnd.getAlfabeto());
        Queue<EstadoAFD> colaAFD = new LinkedList();
        int numEstado = 1;
        EstadoAFD estadoInicial;
        ArrayList<Integer> clausuras = clausurasEpsilonDeEstado(0,afnd);
        ArrayList<Integer> aux1 = new ArrayList<>();
        if (clausuras.size()<=1) {
            if (clausuras.get(0) == 0) {
                estadoInicial = new EstadoAFD("q"+0, clausuras);
                for (int i = 0; i < alfabe.size(); i++) {
                    ArrayList<ArrayList<Integer>> aux2 = buscarAlcanzados(estadoInicial, afnd, alfabe.get(i));
                    for (int j = 0; j < aux2.size(); j++) {
                        for (int k = 0; k < aux2.get(j).size(); k++) {
                            aux1.add(aux2.get(j).get(k));
                        }
                    }
                }
                
            }
        }
        for (int i = 0; i < clausuras.size(); i++) {
            aux1.add(clausuras.get(i));
        }
        ArrayList<Integer> valores = eliminarElementosIguales(aux1);
        estadoInicial = new EstadoAFD("q"+0, valores);
        nuevoAFD.setEstadoInicial(estadoInicial.getValor());
        nuevoAFD.addEstados(estadoInicial.getValor());
        if (buscarFinales(valores, afnd)) {
            nuevoAFD.addEstadosAceptacion(estadoInicial.getValor());
        }
        estados2.add(estadoInicial);
        colaAFD.add(estadoInicial);
        while (!colaAFD.isEmpty()){
            EstadoAFD nuevo = colaAFD.poll();
            
            for (int i = 0; i < alfabe.size(); i++) {
                ArrayList<ArrayList<Integer>> alcanzados2 = null;
                ArrayList<ArrayList<Integer>> alcanzadosEpsilon = new ArrayList<>();
                ArrayList<Integer> finalAlcanzados = new ArrayList<>();
                finalAlcanzados.clear();
                alcanzadosEpsilon.clear();
                alcanzados2 = buscarAlcanzados(nuevo, afnd, alfabe.get(i));
                for (int j = 0; j < alcanzados2.size(); j++) {
                    for (int k = 0; k < alcanzados2.get(j).size(); k++) {
                        alcanzadosEpsilon.add(clausurasEpsilonDeEstado(alcanzados2.get(j).get(k), afnd));
                    }
                    
                }
                
                for (int j = 0; j < alcanzados2.size(); j++) {
                    for (int k = 0; k < alcanzados2.get(j).size(); k++) {
                        finalAlcanzados.add(alcanzados2.get(j).get(k));
                    }
                    
                }
                for (int j = 0; j < alcanzadosEpsilon.size(); j++) {
                    for (int k = 0; k < alcanzadosEpsilon.get(j).size(); k++) {
                        finalAlcanzados.add(alcanzadosEpsilon.get(j).get(k));
                    }
                    
                }
                ArrayList<Integer> aux = eliminarElementosIguales(finalAlcanzados);
                
                if (aux.size()>0) {
                    if (buscarEstadosIguales(aux)) {
                        String nombreAux = nuevo.getValor();
                        String nombreEstado = buscarEstados(aux);
                        TransicionAFD nuevaTransiAFD = new TransicionAFD(nombreAux, alfabe.get(i), nombreEstado);
                        nuevoAFD.addTransiciones(nuevaTransiAFD);

                    }
                    else{

                        EstadoAFD nuevoEstado = new EstadoAFD("q"+numEstado, aux);
                        nuevoEstado.setLetra(alfabe.get(i));
                        colaAFD.add(nuevoEstado);
                        estados2.add(nuevoEstado);
                        numEstado+=1;
                        TransicionAFD nuevaTransiAFD = new TransicionAFD(nuevo.getValor(), nuevoEstado.getLetra(), nuevoEstado.getValor());
                        nuevoAFD.addTransiciones(nuevaTransiAFD);
                        nuevoAFD.addEstados(nuevoEstado.getValor());
                        if (buscarFinales(aux, afnd)) {
                            nuevoAFD.addEstadosAceptacion(nuevoEstado.getValor());
                        }
                    }
                }
                
            }
        }
        nuevoAFD.setAlfabeto(afnd.getAlfabeto());
        
        return nuevoAFD;
    }

}
