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
public final class ParseER {
    ArrayList<AutomataNoDeterminista> automatas = new ArrayList<>();
    ArrayList<Character> caracteres = new ArrayList<>();
    private ArrayList<String> alfabeto = new ArrayList();
    Conversion convertidor = new Conversion();
    String er;
    
    public ParseER (String er, ArrayList<String> alfabeto){
        this.er = er;
        this.alfabeto = alfabeto;
        transformarER(er, automatas);
        extraerCaracteres(er,caracteres);
    } 
    
    public void parsear(){
        int i = 0;
        while (i < caracteres.size()) {  
            if(caracteres.get(i) == '|'){
                i++;
            }
            if(i == caracteres.size()){
                break;
            }
            if (caracteres.get(i) == '.'){   
               AutomataNoDeterminista aux = convertidor.concatenar(automatas.get(i), automatas.get(i+1));
               automatas.remove(i);
               automatas.remove(i);
               caracteres.remove(i);
               automatas.add(i, aux);
            }
        }    
        i = 0;
        while(i < caracteres.size()){
            if (caracteres.get(i) == '|') {
                AutomataNoDeterminista aux = convertidor.disyuncion(automatas.get(i), automatas.get(i+1));
                automatas.remove(i);
                automatas.remove(i);
                caracteres.remove(i);
                automatas.add(i, aux);
            }
        }
        automatas.get(0).setAlfabeto(alfabeto);
        automatas.get(0).mostrarAFND();
    }
        
    public void transformarER(String er, ArrayList<AutomataNoDeterminista> automatas){
        for (int i = 0; i < er.length(); i++) {
            if (i ==er.length()-1 && er.charAt(i) != '*') {
                automatas.add(convertidor.convertirCaracter(er.charAt(i)));
                break;
            }else if (i ==er.length()-1 && er.charAt(i) == '*'){
                break;
            }
            if (er.charAt(i) != '*' && er.charAt(i) != '|' && er.charAt(i) != '.'
                    && er.charAt(i+1) != '*') {
                automatas.add(convertidor.convertirCaracter(er.charAt(i)));
            }
            if (er.charAt(i+1) == '*'){
                automatas.add(convertidor.clausura(convertidor.convertirCaracter(er.charAt(i))));
            }
        }
    }
        
    public void extraerCaracteres(String er, ArrayList<Character> caracteres){
        for (int i = 0; i < er.length(); i++) {
            if (er.charAt(i) == '|' || er.charAt(i) == '.') {
                caracteres.add(er.charAt(i));
            }
        }
    }
}
