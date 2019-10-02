/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ParseER {
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
                    return;
                case "*":
                    return;
            }
        }
    }
}
