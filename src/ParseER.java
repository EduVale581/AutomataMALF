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
    ArrayList <String> er = new ArrayList();
    
    public ParseER (ArrayList<String> er, ArrayList<String> alfabeto){
        this.er = er;
        afnd.setAlfabeto(alfabeto);
    } 
    
    public void parsear(){
        Conversion nueva = new Conversion();
        for (int i = 0; i < er.size(); i++) {  
            AutomataNoDeterminista aux; 
            switch(er.get(i)){
                case "|":
                    return;
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
                    return;       
            }
        }
        afnd.mostrarAFND();
    }
}
