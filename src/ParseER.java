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
    
    public ParseER (ArrayList<String> er){
        this.er = er;
    } 
    
    public void parsear(){
        for (int i = 0; i < er.size(); i++) {  
          switch(er.get(i)){
                case "|":
                    return;
                case ".":
                    Conversion nueva = new Conversion();
                    AutomataNoDeterminista afnd1= nueva.conversionConcatenacion(er.get(i-1).charAt(0), afnd);
                    AutomataNoDeterminista afnd3= nueva.conversionConcatenacion(er.get(i+1).charAt(0), afnd);
                    AutomataNoDeterminista afnd4 = nueva.conversionConcatenacion2(afnd1, afnd3, afnd);
                    return;
                case "*":
                    return;
                
                    
                    
            }
        }
    }
}
