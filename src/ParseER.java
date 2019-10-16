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
        transformarER(this.er, automatas);
        extraerCaracteres(this.er,caracteres);
        parsear(automatas,caracteres);
    } 
      
    public AutomataNoDeterminista parsear(ArrayList<AutomataNoDeterminista> automatas,ArrayList<Character> caracteres){
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
               modificarArray(i, aux,automatas,caracteres);
            }          
        }    
        i = 0;
        while(i < caracteres.size()){
            if (caracteres.get(i) == '|') {
                AutomataNoDeterminista aux = convertidor.disyuncion(automatas.get(i), automatas.get(i+1));
                modificarArray(i, aux,automatas,caracteres);
            }
        }
        automatas.get(0).setAlfabeto(alfabeto);
        
        return automatas.get(0);
    }
   
    public void transformarER(String er, ArrayList<AutomataNoDeterminista> automatas){
        for (int i = 0; i < er.length(); i++) {
            if(er.charAt(i) == '('){
                automatas.add(encontrarParentesis(er, i));
                String sub1 = er.substring(0, i);
                String sub2 = er.substring(getPosParentesis(er)+1, er.length()); 
                er = sub1.concat(sub2);
                this.er = er;
                try{
                    if (er.charAt(i) == '*'){
                        automatas.add(convertidor.clausura(automatas.get(automatas.size()-1)));
                        automatas.remove(automatas.size()-1);
                        er = er.substring(i+1, er.length());
                    } 
                }catch(Exception e){
                    System.out.println("ERRROR "+ i);
                }
            }
            if (i> er.length()-1) {
                break;
            }
            if (i==er.length()-1 && er.charAt(i) != '*' && er.charAt(i) != ')') {
                automatas.add(convertidor.convertirCaracter(er.charAt(i)));
                break;
            }
            else if (i==er.length()-1 && er.charAt(i)=='*' ) {
                break;
            }
            if (er.charAt(i) != '*' && er.charAt(i) != '|' && er.charAt(i) != '.'
                    && er.charAt(i+1) != '*'  ) {
                automatas.add(convertidor.convertirCaracter(er.charAt(i)));
            }
            if (er.charAt(i+1) == '*' ){
                automatas.add(convertidor.clausura(convertidor.convertirCaracter(er.charAt(i))));
            }
            
        }
    }
        
    public void extraerCaracteres(String er, ArrayList<Character> caracteres){
        for (int i = 0; i < er.length(); i++) {
            if ((er.charAt(i) == '|' || er.charAt(i) == '.')) {
                caracteres.add(er.charAt(i));
            }
        }
    }
    
    public void modificarArray(int i, AutomataNoDeterminista aux,ArrayList<AutomataNoDeterminista> automatas,ArrayList<Character> caracteres){
        automatas.remove(i);
        automatas.remove(i);
        caracteres.remove(i);
        automatas.add(i, aux);
    }
    
    public AutomataNoDeterminista encontrarParentesis(String er, int i){
        ArrayList<AutomataNoDeterminista> automatasParentesis = new ArrayList<>();
        ArrayList<Character> caracteresParentesis = new ArrayList<>();
        String erAuxiliar = "";
        for (int j = i+1; j < er.length(); j++) {
            if(er.charAt(j) == '('){
                encontrarParentesis(er, j);
            }
            if (er.charAt(j) == ')') {
                erAuxiliar = er.substring(i+1, j);
                break;
            }
        }

        transformarER(erAuxiliar, automatasParentesis);
        extraerCaracteres(erAuxiliar,caracteresParentesis);
        System.out.println(automatasParentesis.size());
        return parsear(automatasParentesis, caracteresParentesis);
    }
    
    public int getPosParentesis(String er){
        for (int i = 0; i < er.length(); i++) {
            if (er.charAt(i) == ')') {
                return i;
            }
        }
        System.out.println("Hay un parentesis mal puesto...");
        return 0;
    }

}
