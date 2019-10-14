
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardovalenzuela
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean expresionValida = true;
        ArrayList<String> alfabeto = new ArrayList();
        
        //Scanner leer = new Scanner(System.in);
        String er = "(c|w).a.d";
        
        for (int i = 0; i < er.length(); i++) {
            char caracter = er.charAt(i);
            int ascii = (int) caracter;
            if ((!alfabeto.contains(Character.toString(caracter))) && ((ascii > 64 && ascii < 91) 
                    || (ascii > 96 && ascii < 123))){
                alfabeto.add(Character.toString(caracter));
            }
            if (((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)) ||
                    ascii == 46 || ascii == 42 || ascii == 124 || ascii == 95
                    || ascii == 48 || ascii == 45) {                
            }else{
                System.out.println("Ingresastes un simbolo nada que ver xd");
                //expresionValida = false;
               // break;
            }           
        }
       // InfiPost nueva = new InfiPost();
        //String expresion = nueva.Conversion(er);
        if (expresionValida) {
            System.out.println(er);
            ParseER parse = new ParseER(er,alfabeto);
        }
        
    }
    
}