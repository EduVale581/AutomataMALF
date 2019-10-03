/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */
public class InfiPost {
    public String Conversion(String cadena){
        String Expresion, postfija="";  
        int i=0;
        Tipo_Pila Pila = new Tipo_Pila();
        Expresion = cadena;
        char simbolo,elemento;
        while(i<Expresion.length()){
            simbolo=Expresion.charAt(i);
            if(!Pila.esOperador(simbolo))
                postfija+=simbolo;
            else{
                while(!Pila.pilaVacia() &&
                        Pila.Precedencia(Pila.Cima(), simbolo) ){
                    elemento=Pila.quitar();
                    postfija += elemento;
                }
                if (simbolo != ')') 
                    Pila.insertar(simbolo);  
                else           
                    Pila.quitar();
            }
            i++;
        }
        while(!Pila.pilaVacia()){
            elemento = Pila.quitar();
            postfija += elemento;
        }
        return postfija;
        
    }
    
}
