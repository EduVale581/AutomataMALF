/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ParseER {
    AutomataNoDeterminista afnd = new AutomataNoDeterminista();
    String cadena;
    
    public ParseER (String cadena, ArrayList<String> alfabeto){
        this.cadena = cadena;
        afnd.setAlfabeto(alfabeto);
    } 
    
    public void parsear(){
        Conversion nueva = new Conversion();
        
        System.out.println(cadena);
        int i = 0;
        AutomataNoDeterminista aux = new AutomataNoDeterminista();
        while(i<cadena.length()){
            switch (cadena.charAt(i)) {
                case '.':
                    afnd = nueva.concatenar(afnd, aux);
                    i++;
                    break;
                case '|':
                    afnd = nueva.disyuncion(afnd, aux);
                    i++;
                    break;
                default:
                    if (cadena.charAt(i) == '*') {
                        afnd = nueva.clausura(afnd);
                        i++;
                    }
                    else if (cadena.charAt(i+1) == '*'){
                        afnd = nueva.clausura(afnd);
                        i++;
                    }
                    else if (cadena.charAt(i+1) == '*' && afnd.getEstados().isEmpty()) {
                        afnd = nueva.convertirCaracterPrimera(cadena.charAt(i), afnd);
                        afnd = nueva.clausura(afnd);
                    }
                    else if (cadena.charAt(i+1) == '*'){
                        afnd = nueva.clausura(afnd);
                        i++;
                    }
                    else if (afnd.getEstados().isEmpty()) {
                        afnd = nueva.convertirCaracterPrimera(cadena.charAt(i), afnd);
                        aux = nueva.convertirCaracter(cadena.charAt(i+1), afnd);
                        i++;
                        i++;
                    }
                    else{
                        System.out.println(cadena.charAt(i));
                        aux = nueva.convertirCaracter(cadena.charAt(i), afnd);
                        i++;
                    }   break;
            }
        }
        /*for (int i = 0; i < er.size(); i++) {  
            AutomataNoDeterminista aux; 
            switch(er.get(i)){
                case "|":
                    if (afnd.getEstados().isEmpty()) {             
                        afnd = nueva.convertirCaracterPrimera(er.get(i-1).charAt(0), afnd);
                        aux = nueva.convertirCaracter(er.get(i+1).charAt(0), afnd);
                        afnd = nueva.disyuncion(afnd, aux);
                    }else{
                        aux = nueva.convertirCaracter(er.get(i+1).charAt(0), afnd);
                        afnd = nueva.disyuncion(afnd, aux);
                    }
                    break;
                case ".":
                    if (afnd.getEstados().isEmpty()) {             
                        afnd = nueva.convertirCaracterPrimera(er.get(i-1).charAt(0), afnd);
                        aux = nueva.convertirCaracter(er.get(i+1).charAt(0), afnd);
                        afnd = nueva.concatenar(afnd,aux);
                    }else{
                        aux = nueva.convertirCaracter(er.get(i+1).charAt(0), afnd);
                        afnd = nueva.concatenar(afnd, aux);
                    }
                    break;
                case "*":
                    if (afnd.getEstados().isEmpty()) {             
                        afnd = nueva.convertirCaracterPrimera(er.get(i-1).charAt(0), afnd);
                        afnd = nueva.clausura(afnd);
                    }else{
                   //     nueva.convertirCaracter(er.get(i-1).charAt(0), afnd);
                        afnd = nueva.clausura(afnd);
                    }
                    break;       
            }
        }*/
        afnd.mostrarAFND();
    }
}
