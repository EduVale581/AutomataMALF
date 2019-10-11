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
    Conversion convertidor = new Conversion();
    String er;
    
    public ParseER (String er, ArrayList<String> alfabeto){
        this.er = er;
        afnd.setAlfabeto(alfabeto);
    } 
    
    public void parsear(){

        ArrayList<AutomataNoDeterminista> automatas = new ArrayList<>();
        ArrayList<Character> caracteres = new ArrayList<>();
        transformarER(er, automatas);
        extraerCaracteres(er,caracteres);
        int i = 0;int j = 0;int tamanoF = caracteres.size();
        while (i < caracteres.size()) {  
            if(caracteres.get(i) == '|'){
                System.out.println("asd");
                i++;
            }
            if(i == caracteres.size()){
                System.out.println("entre aqui");
                break;
            }
            if (caracteres.get(i) == '.'){   
               System.out.println("XD");
               AutomataNoDeterminista aux = convertidor.concatenar(automatas.get(i), automatas.get(i+1));
               automatas.remove(i);
               automatas.remove(i);
               caracteres.remove(i);
               automatas.add(i, aux);
            }
         //   i++;
            
        }    
        i = 0;tamanoF = caracteres.size();
        while(i < caracteres.size()){
            if (caracteres.get(i) == '|') {
                System.out.println("xd");
                AutomataNoDeterminista aux = convertidor.disyuncion(automatas.get(i), automatas.get(i+1));
                automatas.remove(i);
                automatas.remove(i);
                caracteres.remove(i);
                automatas.add(i, aux);
            }

        }
         automatas.get(0).mostrarAFND();
         //convertidor.conversion2(automatas.get(0));//convertirAFNDaAFD(automatas.get(0));
         
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
       // afnd.mostrarAFND();
        
        public void transformarER(String er, ArrayList<AutomataNoDeterminista> automatas){
            for (int i = 0; i < er.length(); i++) {
                if (i==er.length()-1 && er.charAt(i) != '*') {
                    automatas.add(convertidor.convertirCaracter(er.charAt(i)));
                    break;
                }
                else if (i==er.length()-1 && er.charAt(i)=='*') {
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
            System.out.println("la cantidad de automatas es "+automatas.size());
        }
        
        public void extraerCaracteres(String er, ArrayList<Character> caracteres){
            for (int i = 0; i < er.length(); i++) {
                if (er.charAt(i) == '|' || er.charAt(i) == '.') {
                    caracteres.add(er.charAt(i));
                    System.out.println("puse el caracter "+er.charAt(i)+" en la lista");
                }
            }
    }


    


/* System.out.println(cadena);
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
                    else if (cadena.charAt(i+1) == '*' && afnd.getEstados().isEmpty()) {
                        afnd = nueva.convertirCaracterPrimera(cadena.charAt(i), afnd);
                        afnd = nueva.clausura(afnd);
                        i++;
                    }
                    else if (cadena.charAt(i+1) == '*'){
                        
                        afnd = nueva.convertirCaracter(cadena.charAt(i), afnd);
                        afnd = nueva.clausura(afnd);
                        i++;
                        i++;
                        
                        System.out.println(i);
                    }
                    else if (afnd.getEstados().isEmpty()) {
                        afnd = nueva.convertirCaracterPrimera(cadena.charAt(i), afnd);
                        aux = nueva.convertirCaracter(cadena.charAt(i+1), afnd);
                        i++;
                        i++;
                    }
                    else{
                        aux = nueva.convertirCaracter(cadena.charAt(i), afnd);
                        i++;
                    }
                    break;
            }*/
}
